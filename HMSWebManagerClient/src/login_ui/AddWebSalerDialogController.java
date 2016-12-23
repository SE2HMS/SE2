package login_ui;


import java.net.URL;
import java.util.ResourceBundle;

import VO.RegisterResult;
import VO.RegisterState;
import VO.WebSaler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;

/**
 * Dialog to edit details of a person.
 *
 * @author ObserverZQ
 */
public class AddWebSalerDialogController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField contactField;


    @FXML
    private Button confirm;

    private LoginBlServ bl = new LoginBlServImpl();
    private MainApp mainApp;
    private Stage dialogStage;
    public WebSaler saler; //
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {

        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            RegisterResult result = bl.registerWebSaler(passwordField.getText(), nameField.getText(),
                    contactField.getText());
            if (result.getState().equals(RegisterState.SUCCESS)) {
                okClicked = true;
                showId(result.getId());
                dialogStage.close();
            } else { //result.equals(RegisterState.ALREADY_REGISTERED)
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("错误");
                alert.setContentText("用户名已存在！");
                alert.showAndWait();
            }
        }
    }

    private void showId(String id) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Seccess");
        alert.setContentText("注册成功，id:" + id);
        alert.showAndWait();
    }


    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        LoginBlServ bl = LoginBlServ.getInstance();
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }
        if (contactField.getText() == null || contactField.getText().length() == 0) {
            errorMessage += "No valid contact!\n";
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
}