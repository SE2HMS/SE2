package user_main_ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMainApp extends Application {//看我能不能push

	private Stage primaryStage;
//	privtae AnchorPane
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		showUI();
	}

	public void showUI(){
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(UserMainApp.class.getResource("MainUI.fxml"));
	            AnchorPane Overview = (AnchorPane) loader.load();

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
