package login_ui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import strategy_bl_serv.WebStrategyBlServ;
import strategy_bl_servlmpl.WebStrategyBlServlmpl;

public class StrategydateAddController implements Initializable {

    @FXML
    TextField nameField;
    @FXML
    DatePicker begin;
    @FXML
    DatePicker end;
    @FXML
    TextField discountField;

    @FXML
    Button confirm;
    @FXML
    Button cancel;


    private Stage dialogStage;
    private MainApp mainApp;
    private WebStrategyBlServ serv = new WebStrategyBlServlmpl();
    private boolean okClicked = false;

    @FXML
    public void handleOK() {
        if (isInputValid()) {
            String strategyName = nameField.getText();
            double discount = 0;
            try {
                discount = Double.parseDouble(discountField.getText());
//                System.out.println(discount);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("错误");
                alert.setContentText("折扣格式有误，请重试");
                alert.showAndWait();
                return;
            }
            LocalDate beginDate = begin.getValue();
            LocalDate endDate = end.getValue();
            ZoneId zone = ZoneId.systemDefault();
            Instant instant1 = beginDate.atStartOfDay().atZone(zone).toInstant();
            Date startTime = Date.from(instant1);
            Instant instant2 = endDate.atStartOfDay().atZone(zone).toInstant();
            Date endTime = Date.from(instant2);
            boolean success = serv.addDoubleElevenStrategy(strategyName, discount, startTime, endTime);
            if (success) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("成功！");
                alert.setContentText("新增网站促销策略成功！");
                alert.showAndWait();
                okClicked = true;
                mainApp.showWebSalerMain();
                mainApp.controller.showWebStrategyPane();
                dialogStage.close();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("错误");
                alert.setContentText("促销策略已存在！");
                alert.showAndWait();
            }
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    private boolean isInputValid() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (begin.getValue() == null) {
            errorMessage += "No valid begin date!\n";
        }
        if (end.getValue() == null || end.getValue().isBefore(begin.getValue())) {
            errorMessage += "No valid end date!\n";
        }
        if (discountField.getText() == null || discountField.getText().length() == 0) {
            errorMessage += "No valid discount!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Message");
            alert.setHeaderText("请将新增信息填写完整！");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    public void giveup() {
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
}
