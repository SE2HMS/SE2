package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import VO.UserVO;
import credit_bl_serv.CreditBlServ;
import credit_bl_servlpml.CreditBlServImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import login_bl_servlmpl.LoginBlServImpl;


public class CreditRechargeDialogController implements Initializable{
	private static String id;
	
	private MainApp mainApp;
	
	@FXML
	private Stage stage;
	
	@FXML
	private Label idLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label creditLabel;
	
	@FXML
	private TextField rechargefield;

	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	
	private LoginBlServ userBlServ = LoginBlServ.getInstance();
	private CreditBlServ creditBl = CreditBlServ.getInstance();
	UserVO user;
	
	public void setDialogStage(Stage diaStage) {
		// TODO Auto-generated method stub
		this.stage=diaStage;
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp=mainApp;
	}
	public static void setID(String id){
		CreditRechargeDialogController.id = id;
	}
	
	@FXML
	public void saveAddedCredit(){
		if(rechargefield.getText()==""){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Message");
            alert.setHeaderText("错误");
            alert.setContentText("未填写应增信用值！");
            alert.showAndWait();
		}
		double num = Double.parseDouble(rechargefield.getText());
		boolean success = creditBl.charge(id, num);
		if(success){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText("充值成功！");
            alert.showAndWait();
		}else{
			System.out.println("发生错误！");
		}
	}
	@FXML 
	public void giveup(){
		this.stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		user = userBlServ.getUserInfo(id);
		idLabel.setText(id);
		nameLabel.setText(user.getName());
		creditLabel.setText(String.valueOf(user.getCredit()));
	}
}
