package user_main_ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import login_ui.MainApp;
public class MainController implements Initializable{

	/*
	 * 宸︿晶鑿滃崟鏍�
	 */
	@FXML
	private Button mainUIButton;
	
	@FXML
	private Button personalInfoButton;
	
	@FXML
	private Button creditUIButton;
	
	@FXML
	private Button orderInfoButton;
	
	@FXML
	private Button pastHotelInfoButton;
	
	
	@FXML
	private AnchorPane mainUIPane;
	
	@FXML
	private AnchorPane personalInfoPane;
	
	@FXML
	private AnchorPane creditUIPane;
	
	@FXML
	private AnchorPane orderInfoPane;
	
	@FXML
	private AnchorPane pastHotelInfoPane;
	
	
	
	
	
	@FXML
	private Label userNameLabel;  

    // Reference to the main application.
    private MainApp mainApp;
    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	public void initialize(){
		this.userNameLabel.setText("hehe");
		mainUIPane.setVisible(true);
		
	}
	@FXML
	public void showMainUI(){
		personalInfoPane.setVisible(false);
		creditUIPane.setVisible(false);
		orderInfoPane.setVisible(false);
		pastHotelInfoPane.setVisible(false);
		mainUIPane.setVisible(true);
	}
	@FXML
	public void showPersonalInfo(){
		mainUIPane.setVisible(false);
		creditUIPane.setVisible(false);
		orderInfoPane.setVisible(false);
		pastHotelInfoPane.setVisible(false);
		personalInfoPane.setVisible(true);
	}
	
	@FXML
	public void showCreditUIPane(){
		mainUIPane.setVisible(false);
		personalInfoPane.setVisible(false);
		orderInfoPane.setVisible(false);
		pastHotelInfoPane.setVisible(false);
		creditUIPane.setVisible(true);
	}
	
	@FXML
	public void showOrderInfoPane(){
		mainUIPane.setVisible(false);
		personalInfoPane.setVisible(false);
		creditUIPane.setVisible(false);
		pastHotelInfoPane.setVisible(false);		
		orderInfoPane.setVisible(true);
	}
	
	@FXML
	public void showPastHotelInfoPane(){
		mainUIPane.setVisible(false);
		personalInfoPane.setVisible(false);
		creditUIPane.setVisible(false);
		orderInfoPane.setVisible(false);		
		pastHotelInfoPane.setVisible(true);
	}
	
	
	
	
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
}
