package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 * 
 * @author ObserverZQ
 */
public class AddHotelStaffDialogController implements Initializable{

    @FXML
    private TextField hotelNameField;
  
    @FXML
    private TextField contactField;


    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    
    private Stage dialogStage;
//    private HotelStaffVO staff;
    private boolean okClicked = false;

    private MainApp mainApp;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    
    public void setMainApp(MainApp mainApp){
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
     * Sets the person to be edited in the dialog.
     * 
     * @param hotelStaff
     */
//    public void setHotelStaff(HotelStaffVO staff) {
//        this.staff = staff;
//
//        hotelNameField.setText(staff.getHotelName());
//        contactField.setText(staff.getContact());
//  
//    }

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
//            staff.setHotelName(hotelNameField.getText());
//            staff.setContact(contactField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * **一个酒店只能有一个工作人员**
     * 要检查看酒店名称是否已存在~~~~~~~~！！！
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
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}