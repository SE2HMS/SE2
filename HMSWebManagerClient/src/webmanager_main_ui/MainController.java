package webmanager_main_ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class MainController {

	/*
	 * 左侧菜单栏
	 */
	@FXML
	private Button memberManageButton;
	
	@FXML
	private Button hotelStaffManageButton;
	
	@FXML
	private Button webSalerManageButton;
	
	@FXML
	private AnchorPane memberManagePane;
	
	@FXML
	private AnchorPane hotelStaffManagePane;
	
	@FXML
	private AnchorPane webSalerManagePane;
	
	
	//Reference to the main application
	private WebManagerMainApp mainApp;
	
	AddHotelStaffDialogController controller;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	public void initialize(){
		controller = new AddHotelStaffDialogController();
		memberManagePane.setVisible(true);
	}
	
	/**
	 * 界面跳转
	 */
	@FXML
	public void showMemberManagePane(){
		hotelStaffManagePane.setVisible(false);
		webSalerManagePane.setVisible(false);
		memberManagePane.setVisible(true);
	}
	
	@FXML
	public void showHotelStaffManagePane(){
		memberManagePane.setVisible(false);
		webSalerManagePane.setVisible(false);		
		hotelStaffManagePane.setVisible(true);
	}
	
	@FXML
	public void showWebSalerManagePane(){
		memberManagePane.setVisible(false);
		hotelStaffManagePane.setVisible(false);
		webSalerManagePane.setVisible(true);
		
	}
	
	
	
	
	

	
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(WebManagerMainApp mainApp) {
        this.mainApp = mainApp;
    }
    
}
