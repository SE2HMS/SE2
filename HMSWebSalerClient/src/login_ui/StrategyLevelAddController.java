package login_ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import strategy_bl_serv.WebStrategyBlServ;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/12/24.
 */
public class StrategyLevelAddController implements Initializable{
    private Stage dialogStage;
    private MainApp mainApp;

    @FXML
    private TextField nameField,discountField;

    @FXML
    public void onConfirmClicked() {
        WebStrategyBlServ serv = WebStrategyBlServ.getInstance();
        String name = nameField.getText();
        int upgradeNum;
        try {
            upgradeNum = Integer.parseInt(discountField.getText());
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请在升级所需信用框输入数字");
            alert.showAndWait();
            return;
        }
        boolean success = serv.addLevelStrategy(name,upgradeNum);
        if(success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("添加成功");
            alert.showAndWait();
            mainApp.showWebSalerMain();
            mainApp.controller.showWebStrategyPane();
            this.dialogStage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("发生了一些错误，请稍候重试");
            alert.showAndWait();
        }
    }

    @FXML
    public void onReturnClicked() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
