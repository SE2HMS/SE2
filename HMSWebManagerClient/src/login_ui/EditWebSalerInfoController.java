package login_ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import VO.UserType;
import VO.UserVO;
import VO.WebSaler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;
import manage_bl_serv.ManageBlServ;
import manage_bl_servlmpl.ManageBlServImpl;

public class EditWebSalerInfoController implements Initializable {
    private static String id;

    private MainApp mainApp;

    @FXML
    private Stage stage;

    @FXML
    private TextField namefield;

    @FXML
    private TextField contactfield;


    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    private LoginBlServ userBlServ = new LoginBlServImpl();
    private ManageBlServ modify = new ManageBlServImpl();
    WebSaler saler, modifiedSaler;


    /**
     * 新建一个UserVO，获得会员类型并传入相关信息，保存编辑过的会员信息
     */
    @FXML
    public void saveChangedInfo() {
        String name = namefield.getText();
        String contact = contactfield.getText();

        modifiedSaler = new WebSaler(name, contact);
        modify.modifyWebSaler(modifiedSaler);
    }


    public void setDialogStage(Stage diaStage) {
        // TODO Auto-generated method stub
        this.stage = diaStage;
    }

    public void setMainApp(MainApp mainApp) {
        // TODO Auto-generated method stub
        this.mainApp = mainApp;
    }


    public static void setID(String id) {
        EditWebSalerInfoController.id = id;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        saler = userBlServ.getWebSaler(id);
        namefield.setText(saler.getName());
        contactfield.setText(saler.getContact());
    }

    @FXML
    public void giveup() {
        this.stage.close();
    }


}
