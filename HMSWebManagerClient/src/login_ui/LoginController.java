package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import VO.LoginResult;
import VO.WebManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import login_bl_serv.LoginBlServ;

public class LoginController implements Initializable {

    @FXML
    private Button Confirm;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    private MainApp mainApp;

    @FXML
    public void checkLoginInfo() {
        if (isInputValid()) {
            LoginBlServ l = LoginBlServ.getInstance();
            String id = userNameField.getText();
            LoginResult result = l.login(id, passwordField.getText());
            System.out.println(result.toString());
            if (result.equals(LoginResult.SUCCESS)) {
                mainApp.setCurrentId(userNameField.getText());
                WebManager webManager = LoginBlServ.getInstance().getWebManager(userNameField.getText());
                if (webManager == null) {
                    showWrongAlert();
                    l.logout(id);
                    return;
                }
                mainApp.setCurrentUserName(webManager.getUserName());
                mainApp.showWebManagerMain();
            } else if (result.equals(LoginResult.ALREADY_LOGIN)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error");
                alert.setHeaderText("错误");
                alert.setContentText("您已登录，请勿重复登录");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error");
                alert.setHeaderText("错误");
                alert.setContentText("发生了一些错误，请稍候重试");
                alert.showAndWait();
            }
        } else {
            showWrongAlert();
        }
    }

    private void showWrongAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Message");
        alert.setHeaderText("错误");
        alert.setContentText("用户名或密码错误，请重新输入");
        alert.showAndWait();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (userNameField.getText() == null || userNameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        } else if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
