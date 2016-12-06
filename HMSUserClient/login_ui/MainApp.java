package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteRunner;
import user_main_ui.MainController;
import user_main_ui.MainUIController;

import java.io.IOException;
import java.io.InputStream;

import com.sun.javafx.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {

	private Stage primaryStage,diaStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hotel Management System");
		this.diaStage=new Stage();
		//鐏炴洜銇氶惂璇茬秿閻ｅ矂娼�
		showLoginUI();
	}
	
	
	/**
	 * 鐏炴洜銇氶惂璇茬秿閻ｅ矂娼�
	 */
	public void showLoginUI(){
		try {  
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");  
            login.setMainApp(this);  
        } catch (Exception ex) {  
            ex.printStackTrace();
        }	
	}
	
	public void showUserMain(String userid){
		try {
			MainController controller=(MainController) replaceSceneContent("Main.fxml");
			controller.setMainApp(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * 閺勫墽銇氶柅澶嬪濞夈劌鍞界猾璇茬�烽悾�?勬桨
	 * 
	 */
	public void showRegisterUI() {
		ChooseTypeController controller;
		try {
			controller = (ChooseTypeController) replaceDiaSceneContent("ChooseType.fxml");
			controller.setDialogStage(diaStage);
			controller.setMainApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 閺勫墽銇氶弲顕�锟芥矮绱伴崨妯绘暈閸愬瞼鏅棃锟�?
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
	 * 閺勫墽銇氭导浣风瑹娴兼艾鎲冲▔銊ュ斀閻ｅ矂娼�?
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
	 * 鏉堟挸鍙嗘穱鈩冧紖娑撳秴鐣弫杈剧礉閺勫墽銇氶柨娆掝嚖娣団剝浼�
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
    
    private Initializable replaceSceneContent(String fxml) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream in = MainApp.class.getResourceAsStream(fxml);  
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(MainApp.class.getResource(fxml));  
        AnchorPane page;  
        try {  
            page = (AnchorPane) loader.load(in);  
        } finally {  
            in.close();  
        }   
        Scene scene = new Scene(page);  
        primaryStage.setScene(scene);  
        primaryStage.show();
        return (Initializable) loader.getController();  
    }
    
    private Initializable replaceDiaSceneContent(String fxml) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream in = MainApp.class.getResourceAsStream(fxml);  
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(MainApp.class.getResource(fxml));  
        AnchorPane page;  
        try {  
            page = (AnchorPane) loader.load(in);  
        } finally {  
            in.close();  
        }   
        Scene scene = new Scene(page);  
        diaStage.setScene(scene);  
        diaStage.show();
        return (Initializable) loader.getController();  
    }

    
    
	public static void main(String[] args) {
		RemoteRunner cr = new RemoteRunner();
		launch(args);
	}
}
