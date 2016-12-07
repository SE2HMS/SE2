package webmanager_main_ui;
import javafx.scene.control.*;
import javafx.fxml.*;

public class HotelStaffManageController {

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
	private void handleNewHotelStaff() {
	    HotelStaffVO tempStaff = new HotelStaffVO();
	    boolean okClicked = mainApp.showAddHotelStaffDialog(tempStaff);
	    if (okClicked) {
	    	//往list中添加一个staff
	    }
	}
	
}
