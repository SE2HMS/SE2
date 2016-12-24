package login_ui;

import VO.UserVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import login_bl_serv.LoginBlServ;

public class CreditRechargeController {
    @FXML
    private TextField inputField;
    @FXML
    private Button search;
    @FXML
    private Button recharge;
    @FXML
    private AnchorPane infoPane;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label creditLabel;


    //Reference to the main application
    private static MainApp mainApp;
    private LoginBlServ userBlServ = LoginBlServ.getInstance();
    private UserVO user = null;
    private String id = "";

	
	/*
	 * 输入会员ID后点击搜索按钮，显示相应会员信息
	 */
    @FXML
    public void search() {
        id = inputField.getText();
        user = userBlServ.getUserInfo(id);
        if (user == null) {
            id = "";
            setLabelEmpty();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("该会员ID不存在，请重新输入");
            alert.showAndWait();
            return;
        }
        infoPane.setVisible(true);
        idLabel.setText(String.valueOf(id));
        nameLabel.setText(user.getName());
        creditLabel.setText(String.valueOf(user.getCredit()));
    }

    private void setLabelEmpty() {
        idLabel.setText("");
        nameLabel.setText("");
        creditLabel.setText("");
    }

    @FXML
    public void openCreditRecharge() {
        if(id == null || id.equals("")) {
            return;
        }
        mainApp.showCreditRecharge(id);
    }

    public static void setMainApp(MainApp mainApp) {
        CreditRechargeController.mainApp = mainApp;
    }

}
