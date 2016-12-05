package user_main_ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
public class MainUIController {

	@FXML
	private Label idLabel;  //id
	
	@FXML
	private Label userNameLabel; //用户名
	
	@FXML
	private Label nameLabel; //姓名
	
	
	@FXML
	private Label birthdayLabel; //出生日期
	
	@FXML
	private Label BusiLabel;  //企业名称
	
	@FXML
	private Label phoneLabel;  //手机号
	
	@FXML
	private Label memberLevelLabel; //会员等级
	

	
	
//	//用户主界面的可选项，首页，个人资料，信用记录，订单信息，过往酒店信息
//	private ObservableList<String> options = FXCollections.observableArrayList("首页",
//			"个人资料","信用记录","订单信息","过往酒店信息");
//	
//	@FXML
//	private ListView<String> listView = new ListView<>(options);

    // Reference to the main application.
    private UserMainApp mainApp;
    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	public void initialize(){
//		listView.setItems(options);

	}
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(UserMainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
//        listView.setItems(options);
//        listView.getSelectionModel().get
    }
    
}
