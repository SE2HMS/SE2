package login_ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import VO.UserType;
import VO.UserVO;
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

public class EditMemberInfoDialogController implements Initializable{
	private static String id;
	
	private MainApp mainApp;
	
	@FXML
	private Stage stage;
	
	@FXML
	private TextField namefield;
	
	@FXML
	private TextField companyfield;
	
	@FXML
	private TextField contactfield;
	
	@FXML
	private DatePicker bdayPicker;
	
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	
	private LoginBlServ userBlServ = new LoginBlServImpl();
	private ManageBlServ modify = new ManageBlServImpl();
	UserVO user,modifiedUser;
	
	
	/**
	 * 新建一个UserVO，获得会员类型并传入相关信息，保存编辑过的会员信息
	 */
	@FXML
	public void saveChangedInfo(){
		String name = namefield.getText();
		String contact = contactfield.getText();
		UserType type = user.getType();
		String additionalInfo = "";
		if(type==UserType.NORMAL){
			LocalDate date = bdayPicker.getValue();
			additionalInfo = date.toString();
		}else{
			additionalInfo = companyfield.getText();
		}		
		modifiedUser = new UserVO(name, contact, type, additionalInfo);
		modify.modifyUserInfo(modifiedUser);
	}
	
	
	public void setDialogStage(Stage diaStage) {
		// TODO Auto-generated method stub
		this.stage=diaStage;
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp=mainApp;
	}
	
	
	public static void setID(String id){
		EditMemberInfoDialogController.id=id;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		user=userBlServ.getUserInfo(id);
		if(user.getType().equals(UserType.NORMAL)){
			companyfield.setText("无");
			companyfield.setEditable(false);
			LocalDate bday = LocalDate.parse(user.getAdditionalInfo()); //生日格式应为yyyy-MM-dd
			bdayPicker.setValue(bday);
		}
		else{
			bdayPicker.setValue(null);
			bdayPicker.setEditable(false);
			
			companyfield.setText(user.getAdditionalInfo());
		}
		namefield.setText(user.getName());
		contactfield.setText(user.getContact());
	}
	
	@FXML 
	public void giveup(){
		this.stage.close();
	}

	

}
