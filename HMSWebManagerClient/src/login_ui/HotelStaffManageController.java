package login_ui;

import VO.HotelStaff;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;

public class HotelStaffManageController {

    @FXML
    private Hyperlink addNew; //新增

    @FXML
    private TextField inputField;

    @FXML
    private Button search;
    @FXML
    private Button edit;
    @FXML
    private AnchorPane infoPane; //显示搜索出来的酒店工作人员信息的pane
    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label contactLabel;

    //Reference to the main application
    private static MainApp mainApp;
    private LoginBlServ staffBlServ = new LoginBlServImpl();
    HotelStaff staff = null;
    String id = "";

    /*
     * 输入酒店工作人员ID后点击搜索按钮，显示相应工作人员信息
     */
    @FXML
    public void search() {
        id = inputField.getText();
        staff = staffBlServ.getHotelStaff(id);
        if (staff == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("该酒店工作人员ID不存在，请重新输入");
            alert.showAndWait();
            return;
        }
        infoPane.setVisible(true);
        hotelNameLabel.setText(staff.getHotelName());
        idLabel.setText(id);
        nameLabel.setText(staff.getName());
        contactLabel.setText(staff.getContact());
    }


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new hotel staff.
     */
    @FXML
    private void handleNewHotelStaff() {
        mainApp.showAddHotelStaffDialog();
    }

    @FXML
    private void editHotelStaffInfo() {
        mainApp.editHotelStaff(id);
    }

    public static void setMainApp(MainApp mainApp) {
        HotelStaffManageController.mainApp = mainApp;
    }
}
