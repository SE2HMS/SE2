package webmanager_main_ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class WebSalerManageController {
	@FXML
	private Hyperlink addNew; //新增
	
	@FXML
	private TextField inputField;
	
	@FXML
	private Button search;
	
	//Reference to the main application
	private WebManagerMainApp mainApp;
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new hotel staff.
	 */
	@FXML
	private void handleNewWebSaler() {
	    WebSalerVO tempSaler = new WebSalerVO();
	    boolean okClicked = mainApp.showAddWebSalerDialog(tempSaler);
	    if (okClicked) {
	    	//往list中添加一个staff
	    }
	}
}
