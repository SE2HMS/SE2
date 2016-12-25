package login_ui;

import VO.LevelStrategy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import strategy_bl_serv.WebStrategyBlServ;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/12/24.
 */
public class StrategyLevelEditController implements Initializable{

    @FXML
    private TextField nameField,upgradeNumField;

    private Stage dialogStage;
    private MainApp mainApp;
    private String strategyName;
    private WebStrategyBlServ serv = WebStrategyBlServ.getInstance();

    @FXML
    public void onSaveClicked() {
        int upgradeNum;
        try {
            upgradeNum = Integer.parseInt(upgradeNumField.getText());
        }catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(this.dialogStage);
            alert.setContentText("请在升级所需信用输入整数");
            alert.showAndWait();
            return;
        }
        boolean success = serv.modifyLevelStrategy(strategyName,upgradeNum);
        if(success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(this.dialogStage);
            alert.setContentText("修改成功");
            alert.showAndWait();
            mainApp.showWebSalerMain();
            mainApp.controller.showWebStrategyPane();
            this.dialogStage.close();
        }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(this.dialogStage);
            alert.setContentText("发生了一些错误，请稍后重试");
            alert.showAndWait();
        }
    }

    @FXML
    public void onReturnClicked() {
        this.dialogStage.close();
    }

    public void setup(Stage dialogStage,MainApp mainApp,String strategyName) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;
        this.strategyName = strategyName;
        nameField.setText(strategyName);
        LevelStrategy levelStrategy = serv.getLevelStrategyByName(strategyName);
        upgradeNumField.setText(levelStrategy.getUpgradeNum() + "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
