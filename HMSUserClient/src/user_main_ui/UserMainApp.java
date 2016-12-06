package user_main_ui;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMainApp extends Application {//�����ܲ���push

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		showMain();
	}

	public void showMain(){
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(UserMainApp.class.getResource("Main.fxml"));
	            AnchorPane Overview = (AnchorPane) loader.load();

	            MainController mainController = loader.getController();
	            mainController.setMainApp(this);
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(Overview);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		
	}
	

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	public static void main(String[] args) {
		launch(args);
	}
}
