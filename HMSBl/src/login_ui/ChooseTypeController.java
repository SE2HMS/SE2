package login_ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseTypeController {


	@FXML
	private Button Normal;  //普通会员
	
	@FXML
	private Button Busi; //企业会员
	
	@FXML
	private Stage dialogStage;
	
	MemberType type;
	
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public MemberType getType(){
    	return type;
    }
    
    @FXML
    private void normal() {
    	type = MemberType.NORMAL;
    	dialogStage.close();
    }	
    

    @FXML
    private void busi() {
    	type = MemberType.BUSI;
    	dialogStage.close();    	
    }
 
 
}
