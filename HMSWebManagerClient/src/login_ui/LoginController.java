package login_ui;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

	@FXML
	private Button Confirm;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField passwordField;
	
	
	

	
	

	
	
	private MainApp mainApp;



    


     @FXML
     public void checkLoginInfo(){
    	 if(isInputValid()){
//    		 LoginBlServ l=new LoginBlServImpl();
//    		 LoginResult result=l.login(userNameField.getText(), passwordField.getText());
//    		 if(result.equals(LoginResult.SUCCESS)){
    			 mainApp.showWebManagerMain();
//    		 }
//    		 else
//    			 System.out.println(result);
    	 }
    	 else{
    	
    	     Alert alert = new Alert(AlertType.INFORMATION);
    	     alert.initOwner(mainApp.getPrimaryStage());
    	     alert.setTitle("Message");
    	     alert.setHeaderText("错误");
    	     alert.setContentText("用户名或密码错误，请重新输入");

    	     alert.showAndWait();    		 
    	 }
     }
     

     
     /**
      * Initializes the controller class. This method is automatically called
      * after the fxml file has been loaded.
      */
 	@FXML
 	public void initialize(){
 	}
 	
 	
 	private boolean isInputValid(){
 		String errorMessage = "";
 		
 		if(userNameField.getText()==null || userNameField.getText().length()==0){
 			errorMessage += "No valid username!\n";
 		}
 		else if(passwordField.getText()==null || passwordField.getText().length()==0){
 			errorMessage += "No valid password!\n";
 		}
 		
 		if(errorMessage.length()==0){
 			return true;
 		}else{
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
		// TODO Auto-generated method stub
		
	}
}
