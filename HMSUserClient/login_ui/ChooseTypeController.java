package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseTypeController implements Initializable{


	@FXML
	private Button Normal;  //鏅�氫細鍛�?
	
	@FXML
	private Button Busi; //浼佷笟浼氬憳
	
	@FXML
	private Stage dialogStage;
	

	private MainApp mainApp;
	
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    
    @FXML
    private void normal() {
    	mainApp.showNormalUI();
    	dialogStage.close();
    }	
    

    @FXML
    private void busi() {
    	mainApp.showBusiUI();
    	dialogStage.close();    	
    }
 
    public void setMainApp(MainApp mainApp){
    	this.mainApp=mainApp;
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
