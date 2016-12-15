package login_ui;

import VO.WebSaler;
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

public class WebSalerManageController {
	@FXML
	private Hyperlink addNew; //新增
	
	@FXML
	private TextField inputField;
	

	
	@FXML
	private Button search;
	@FXML
	private Button edit;
	@FXML
	private AnchorPane infoPane; //显示搜索出来的网站营销人员信息的pane
	@FXML
	private Label nameLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label contactLabel;
	//Reference to the main application
	private static MainApp mainApp;
	private LoginBlServ salerBlServ=new LoginBlServImpl();
	WebSaler saler = null;
	String id = "";
	
	/*
	 * 输入网站营销人员ID后点击搜索按钮，显示相应工作人员信息
	 */
	@FXML
	public void search(){
		id = inputField.getText();
		saler = salerBlServ.getWebSaler(id);
		if(saler==null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Message");
			alert.setHeaderText("错误");
			alert.setContentText("该网站营销人员ID不存在，请重新输入");
			alert.showAndWait();
			return;
		}
		infoPane.setVisible(true);
		idLabel.setText(id);
		nameLabel.setText(saler.getName());
		contactLabel.setText(saler.getContact());
	}
		
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new hotel staff.
	 */
	@FXML
	private void handleNewWebSaler() {
//	    WebSaler tempSaler = new WebSaler();
//	    boolean okClicked = mainApp.showAddWebSalerDialog(); //括号内应为tempSaler
		mainApp.showAddWebSalerDialog();
//		okClicked = true;
//	    if (okClicked) {
//	    	//往list中添加一个staff
//	    }
	}
	
	@FXML
	public void editWebSalerInfo(){
		mainApp.editWebSaler(id);
	}	
	
	public static void setMainApp(MainApp mainApp){
		WebSalerManageController.mainApp = mainApp;
	}
}
