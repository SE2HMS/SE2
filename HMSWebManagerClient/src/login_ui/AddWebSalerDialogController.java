package login_ui;


import java.net.URL;
import java.util.ResourceBundle;

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
public class AddWebSalerDialogController implements Initializable{

    @FXML
    private TextField nameField;
  
    @FXML
    private TextField contactField;


    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    
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
     * Sets the person to be edited in the dialog.
     * 
     * @param hotelStaff
     */
    public void setWebSaler(WebSaler saler) {
        this.saler = saler;

        nameField.setText(saler.getName());
        contactField.setText(saler.getContact());
  
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
//           setName(nameField.getText());
//           setContact(contactField.getText());

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
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        LoginBlServ bl = new LoginBlServImpl();
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (contactField.getText() == null || contactField.getText().length() == 0) {
            errorMessage += "No valid contact!\n"; 
        }
//        RegisterResult result = bl.registerWebSaler(id, password, name, contact);


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
    
    public void setMainApp(MainApp mainApp){
    	this.mainApp = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}