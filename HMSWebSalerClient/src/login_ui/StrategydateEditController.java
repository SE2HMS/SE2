package login_ui;

import VO.DoubleElevenStrategy;
import com.sun.javafx.menu.CustomMenuItemBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import strategy_bl_serv.WebStrategyBlServ;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StrategydateEditController implements Initializable{
    @FXML
    private TextField nameField,discountField;

    @FXML
    private DatePicker startTimePicker,endTimePicker;

    private Stage dialogStage;
    private MainApp mainApp;
    private String strategyName;
    private WebStrategyBlServ serv = WebStrategyBlServ.getInstance();

    @FXML
    private void onSaveClicked() {
        LocalDate start = startTimePicker.getValue();
        LocalDate end = endTimePicker.getValue();
        double discount = 0;
        try {
            discount = (Double.parseDouble(discountField.getText()));
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请输入正确的折扣格式");
            alert.showAndWait();
            return;
        }
        boolean success = serv.modifyDoubleElevenStrategy(strategyName,start,end,discount);
        if(success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("修改成功");
            alert.showAndWait();
            mainApp.showWebSalerMain();
            mainApp.controller.showWebStrategyPane();
            this.dialogStage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("发生了一些异常，请稍候重试");
            alert.showAndWait();
        }
    }

    @FXML
    private void onReturnClicked() {
        this.dialogStage.close();
    }

    public void setup(Stage dialogStage,MainApp mainApp,String strategyName) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;
        this.strategyName = strategyName;
        DoubleElevenStrategy strategy = serv.getDoubleElevenStrategyByName(strategyName);
        nameField.setText(strategy.getName());
        Calendar startDate = new Calendar.Builder().setInstant(strategy.getStartTime()).build();
        Calendar endDate = new Calendar.Builder().setInstant(strategy.getEndTime()).build();
        LocalDate start = LocalDate.of(startDate.get(Calendar.YEAR),startDate.get(Calendar.MONTH) + 1,startDate.get(Calendar.DAY_OF_MONTH));
        LocalDate end = LocalDate.of(endDate.get(Calendar.YEAR),endDate.get(Calendar.MONTH) + 1,endDate.get(Calendar.DAY_OF_MONTH));
        startTimePicker.setValue(start);
        endTimePicker.setValue(end);
        discountField.setText(strategy.getDiscount() + "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
