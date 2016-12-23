package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import VO.HotelStaff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import manage_bl_serv.ManageBlServ;

public class EditHotelStaffInfoController implements Initializable {
    private static String id;
    private MainApp mainApp;
    @FXML
    private Stage stage;
    @FXML
    private TextField namefield;
    @FXML
    private TextField contactfield;

    private LoginBlServ loginBlServ = LoginBlServ.getInstance();
    private ManageBlServ manageBlServ = ManageBlServ.getInstance();

    /**
     * 新建一个UserVO，获得会员类型并传入相关信息，保存编辑过的会员信息
     */
    @FXML
    public void saveChangedInfo() {
        String name = namefield.getText();
        String contact = contactfield.getText();
        manageBlServ.modifyHotelStaff(id,name,contact);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(this.stage);
        alert.setContentText("修改成功");
        alert.showAndWait();
        this.stage.close();
    }

    public void setDialogStage(Stage diaStage) {
        this.stage = diaStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public static void setID(String id) {
        EditHotelStaffInfoController.id = id;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        HotelStaff staff = loginBlServ.getHotelStaff(id);
        String hotelname = staff.getHotelName();
        namefield.setText(hotelname);
        contactfield.setText(staff.getContact());
    }

    @FXML
    public void giveup() {
        this.stage.close();
    }

}
