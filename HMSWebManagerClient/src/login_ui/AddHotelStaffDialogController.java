package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import VO.HotelStaff;
import VO.RegisterResult;
import VO.RegisterState;
import VO.UserLoginInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;
import manage_bl_serv.ManageBlServ;
import manage_bl_servlmpl.ManageBlServImpl;

/**
 * Dialog to edit details of a person.
 *
 * @author ObserverZQ
 */
public class AddHotelStaffDialogController implements Initializable {

    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField contactField;


    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private Stage dialogStage;
    private HotelStaff staff;
    private boolean okClicked = false;
    private LoginBlServ staffBlServ = new LoginBlServImpl();


    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
            RegisterResult result = staffBlServ.registerHotelStaff(passwordField.getText(),
                    hotelNameField.getText(), contactField.getText(), userNameField.getText());
            if (result.getState().equals(RegisterState.SUCCESS)) {
                okClicked = true;
                showId(result.getId());
                dialogStage.close();
            } else { //result.equals(RegisterState.ALREADY_REGISTERED)
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("错误");
                alert.setContentText("酒店名称已存在（一个酒店只能拥有一个工作人员账号）！");
                alert.showAndWait();
            }
        }
    }

    private void showId(String id) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Success");
        alert.setContentText("注册成功，id:" + id);
        alert.showAndWait();
    }


    /**
     * Validates the user input in the text fields.
     * **一个酒店只能有一个工作人员**
     * 要检查看酒店名称是否已存在~~~~~~~~！！！
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (hotelNameField.getText() == null || hotelNameField.getText().length() == 0) {
            errorMessage += "No valid hotel name!\n";
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }


    @FXML
    public void giveup() {
        this.dialogStage.close();
    }
}