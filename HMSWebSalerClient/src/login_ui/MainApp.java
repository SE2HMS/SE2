package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteRunner;


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
	
	public void showWebSalerMain(){
		try {
			CreditRechargeController.setMainApp(this);
			OrderTableController.setUp(this);  
			OrderInfoController.setUp(this);
			MainController controller=(MainController) replaceSceneContent("Main.fxml");
			controller.setMainApp(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showCreditRecharge(String id){
		CreditRechargeDialogController controller;
		CreditRechargeDialogController.setID(id);
		try {
			controller=(CreditRechargeDialogController) replaceSceneContent("CreditRechargeDialog.fxml");
			controller.setDialogStage(diaStage);
			controller.setMainApp(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showOrderDetailsUI(String orderID){
		OrderDetailsController controller;
		OrderDetailsController.setUp(this, orderID);
		try {
			controller = (OrderDetailsController) replaceDiaSceneContent("OrderDetailsUI.fxml");
			controller.setDialogStage(diaStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addNewWebStrategy(){
		
	}
	public void showWebStrategyDetails(String strategyName){
		
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
