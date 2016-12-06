package login_ui;



import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterBusiController {

	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField passwordField;
	
	@FXML
	private TextField passwordConfirmField;
	
	@FXML
	private TextField enterpriseField;
	
	@FXML
	private TextField contactField;
	
	
	@FXML
	public void handleOK(){
		if(isInputValid()){
			String name = userNameField.getText();
			String contact = contactField.getText();
			String password = passwordField.getText();
//			UserVO = new UserVO();
			MemberType type = MemberType.BUSI;
			String enterprise = enterpriseField.getText();
			
			
		}
	}
	
	
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
 		else if(enterpriseField.getText()==null || enterpriseField.getText().length()==0){
 			errorMessage += "No valid enterprise!\n";
 		}
 		else if(contactField.getText()==null || contactField.getText().length()==0){
 			errorMessage += "No valid telephone number!\n";
 		}
 		
 		if(errorMessage.length()==0){
 			return true;
 		}else{
// 			Dialogs.create()
// 			.title("Invalid fields")
// 			.masthead("Please fill in the invalid fields")
// 			.message(errorMessage)
// 			.showError();
 			return false;
 		}	
 	}
}
