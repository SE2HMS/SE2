package login_ui;

import VO.UserType;
import VO.UserVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;

public class MemberManageController {
    @FXML
    private TextField inputField;

    @FXML
    private Button search;
    @FXML
    private Button edit;
    @FXML
    private AnchorPane infoPane; //显示搜索出来的会员信息的pane

    @FXML
    private Label gradeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label creditLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private Label contactLabel;

    //Reference to the main application
    private static MainApp mainApp;
    private LoginBlServ userBlServ = new LoginBlServImpl();
    UserVO user = null;
    String id = "";
    private boolean infoShowing = false;

    /*
     * 输入会员ID后点击搜索按钮，显示相应会员信息
     */
    @FXML
    public void search() {
        id = inputField.getText();
        user = userBlServ.getUserInfo(id);
        if (user == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("该会员ID不存在，请重新输入");
            alert.showAndWait();
            return;
        }
        if (user.getType().equals(UserType.NORMAL)) {
            infoPane.setVisible(true);
            birthdayLabel.setText(user.getAdditionalInfo());
            companyLabel.setText("无");

        } else if (user.getType().equals(UserType.SPECIAL)) {
            infoPane.setVisible(true);
            birthdayLabel.setText("无");
            companyLabel.setText(user.getAdditionalInfo());
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("该会员ID不存在，请重新输入");
            alert.showAndWait();
            return;
        }
        gradeLabel.setText(String.valueOf(user.getGrade()));
        nameLabel.setText(user.getName());
        creditLabel.setText(String.valueOf(user.getCredit()));
        contactLabel.setText(user.getContact());
        infoShowing = true;
    }

    @FXML
    public void editMemberInfo() {
        if(infoShowing) {
            mainApp.editMemberInfo(id);
        }
    }

    public static void setMainApp(MainApp mainApp) {
        MemberManageController.mainApp = mainApp;
    }
}
