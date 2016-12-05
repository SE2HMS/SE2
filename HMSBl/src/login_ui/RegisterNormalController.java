package login_ui;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegisterNormalController {

	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField passwordField;
	
	@FXML
	private TextField passwordConfirmField;
	
	@FXML
	private DatePicker birthday;
	
	@FXML
	private TextField contactField;
	
 	/**
 	 *检查输入是否完整
 	 */
 	private boolean isInputValid(){
 		String errorMessage = "";
 		
 		if(userNameField.getText()==null || userNameField.getText().length()==0){
 			errorMessage += "No valid username!\n";
 		}
 		else if(passwordField.getText()==null || passwordField.getText().length()==0){
 			errorMessage += "No valid password!\n";
 		}
 		else if(passwordConfirmField.getText()==null || passwordConfirmField.getText().length()==0
 				|| (!passwordConfirmField.getText().equals(passwordField.getText())) ){
 			errorMessage += "No valid confirm password!\n";
 		}
 		else if(birthday.getValue()==null){
 			errorMessage += "No valid birthday!\n";
 		}
 		else if(contactField.getText()==null || contactField.getText().length()==0){
 			errorMessage += "No valid telephone number!\n";
 		}
 		
 		if(errorMessage.length()==0){
 			return true;
 		}else{
 			Dialogs.create()
 			.title("Invalid fields")
 			.masthead("Please fill in the invalid fields")
 			.message(errorMessage)
 			.showError();
 			return false;
 		}	
 	}
}
