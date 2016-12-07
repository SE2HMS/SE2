package login_ui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import VO.LoginResult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;

public class LoginController implements Initializable{

	@FXML
	private Button Confirm;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField passwordField;
	
	
	@FXML
	private Hyperlink signUp;
	
	@FXML
	private Button okButton ; //闁挎瑨顕ら幓鎰仛閻ㄥ嚠K閹稿鎸�?
	
	
	public Stage errorStage = new Stage();//闁挎瑨顕ら幓鎰仛閻ㄥ嚪tage
	
	//鐎电ain application閻ㄥ嫬绱╅悽锟�
	private MainApp mainApp;


    /**
     * 閻愮懓鍤�?垾婊勬暈閸愬本鏌婄拹锕�褰块垾婵囧ⅵ瀵拷濞夈劌鍞介悾宀勬桨
     */
    @FXML
    public void register(){
    	 mainApp.showRegisterUI();
    }
    


     @FXML
     public void checkLoginInfo(){
    	 if(isInputValid()){
    		 LoginBlServ l=new LoginBlServImpl();
    		 LoginResult result=l.login(userNameField.getText(), passwordField.getText());
    		 if(result.equals(LoginResult.SUCCESS)){
    			 mainApp.showUserMain(userNameField.getText());
    		 }
    		 else
    			 System.out.println(result);
    	 }
    	 else{
    		 try{
    				
    			FXMLLoader loader = new FXMLLoader();
   				loader.setLocation(MainApp.class.getResource("AlertDialog_css.fxml"));
   				GridPane page = (GridPane) loader.load();
    				
   				this.errorStage.setTitle("Message");
   				Scene scene = new Scene(page);
   				this.errorStage.setScene(scene);
   				this.errorStage.show();
    			
    		}catch(IOException e){
    			e.printStackTrace();
    		}	    		 
    	 }
     }
     
     @FXML
     public void closeErrorMessage(){
    	//bug
    	 this.errorStage.close();
     }
     
     /**
      * Initializes the controller class. This method is automatically called
      * after the fxml file has been loaded.
      */
 	@FXML
 	public void initialize(){
 	}
 	
 	/**
 	 *濡拷閺屻儴绶崗銉︽Ц閸氾箑鐣弫锟�?
 	 */
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
