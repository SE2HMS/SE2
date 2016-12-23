package login_ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

import VO.LoginResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;

public class LoginController implements Initializable {

    @FXML
    private Button Confirm;

    @FXML
    private Button registerButton;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Hyperlink signUp;

    @FXML
    private MainApp mainApp;

    @FXML
    public void checkLoginInfo() {
        if (isInputValid()) {
            LoginBlServ l = new LoginBlServImpl();
            LoginResult result = l.login(userNameField.getText(), passwordField.getText());
            if (result.equals(LoginResult.SUCCESS)) {
                mainApp.setCurrentId(userNameField.getText());
                mainApp.showWebSalerMain();
            } else {
                String info = "";
                switch (result) {
                    case ALREADY_LOGIN:
                        info = "您已登陆，请勿重复登录";
                        break;
                    case EXCEPTION:
                        info = "网络异常，请稍后重试";
                        break;
                    case WRONG_ID:
                        info = "账户不存在";
                        break;
                    case WRONG_PASSWORD:
                        info = "密码错误，请重试";
                        break;
                }
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setContentText(info);
                alert.showAndWait();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("您输入的id或密码格式错误，请重新输入");
            alert.showAndWait();
        }
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
