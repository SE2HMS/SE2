package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.InputStream;

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
	
		showLoginUI();
	}
	
	
	/**
	 * 显示登录界面
	 */
	public void showLoginUI(){
		try {  
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");  
            login.setMainApp(this);  
        } catch (Exception ex) {  
            ex.printStackTrace();
        }	
	}
	
	/**
	 * 显示主页
	 */
	public void showWebManagerMain(){
		try {
			HotelStaffManageController.setMainApp(this);
			WebSalerManageController.setMainApp(this);
			
			MainController controller=(MainController) replaceSceneContent("Main.fxml");
			controller.setMainApp(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id 要编辑信息的会员的id
	 */
	public void editMemberInfo(String userID){
		EditMemberInfoDialogController controller;
		EditMemberInfoDialogController.setID(userID);
		try {
			// Set the person into the controller.
			controller = (EditMemberInfoDialogController)replaceDiaSceneContent("EditMemberInfoDialog.fxml");
			controller.setDialogStage(this.diaStage);
			controller.setMainApp(this);


//        return controller.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
//        return false;
		}
	}
	
	public void showAddHotelStaffDialog() { 
		AddHotelStaffDialogController controller;
		try {
			// Set the person into the controller.
			controller = (AddHotelStaffDialogController)replaceDiaSceneContent("AddHotelStaffDialog.fxml");
			controller.setDialogStage(this.diaStage);
			controller.setMainApp(this);
			

//        return controller.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
//        return false;
		}
	}
	
	public void editHotelStaff(String id) { 
		EditHotelStaffInfoController controller;
		try {
			// Set the person into the controller.
			controller = (EditHotelStaffInfoController)replaceDiaSceneContent("EditHotelStaffInfo.fxml");
			controller.setDialogStage(this.diaStage);
			controller.setMainApp(this);
			controller.setID(id);
//        return controller.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
//        return false;
		}
	}
	
	
	public void showAddWebSalerDialog() { //括号内应为WebSaler saler
		AddWebSalerDialogController controller;
		try {
			// Set the person into the controller.
			controller = (AddWebSalerDialogController)replaceDiaSceneContent("AddWebSalerDialog.fxml");
			controller.setDialogStage(this.diaStage);
			controller.setMainApp(this);
			
//        controller.setWebSaler(saler);

//        return controller.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
//        return false;
		}
	}
	
	public void editWebSaler(String id) { 
		EditWebSalerInfoController controller;
		try {
			// Set the person into the controller.
			controller = (EditWebSalerInfoController)replaceDiaSceneContent("EditWebSalerInfo.fxml");
			controller.setDialogStage(this.diaStage);
			controller.setMainApp(this);
			controller.setID(id);
//        return controller.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
//        return false;
		}
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
//		RemoteRunner cr = new RemoteRunner();
		launch(args);
	}
}
