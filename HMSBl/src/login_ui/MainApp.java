package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteRunner;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hotel Management System");
		
		//灞曠ず鐧诲綍鐣岄潰
		showLoginUI();
	}
	
	
	/**
	 * 灞曠ず鐧诲綍鐣岄潰
	 */
	public void showLoginUI(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Login.fxml"));
			AnchorPane loginUI = (AnchorPane) loader.load();
		
			//Give the controller access to the main app
			LoginController controller = loader.getController();
			controller.setMainApp(this);
			
			//Show the scene containing the loginUI
			Scene scene = new Scene(loginUI);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	

	/**
	 * 鏄剧ず閫夋嫨娉ㄥ唽绫诲瀷鐣岄潰
	 */
	public MemberType showRegisterUI(){
		try{
	
			// Load the fxml file and create a new stage for the choosetype dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("ChooseType.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Register");
			
			ChooseTypeController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
			
			return controller.getType();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;		
	}
	
	
	/**
	 * 鏄剧ず鏅�氫細鍛樻敞鍐岀晫闈�
	 */
	public void showNormalUI(){
		try{
	
			// Load the fxml file and create a new stage for the choosetype dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Register(Normal).fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Register");
			
			Scene scene = new Scene(page);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * 鏄剧ず浼佷笟浼氬憳娉ㄥ唽鐣岄潰
	 */
	public void showBusiUI(){
		try{
//			typeStage.close();
			// Load the fxml file and create a new stage for the choosetype dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Register(Busi).fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Register");
			
			Scene scene = new Scene(page);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * 杈撳叆淇℃伅涓嶅畬鏁达紝鏄剧ず閿欒淇℃伅
	 */
	public void showErrorMessage(){
	
	}
	
	
	/**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
	public static void main(String[] args) {
		RemoteRunner cr = new RemoteRunner();
		launch(args);
	}
}
