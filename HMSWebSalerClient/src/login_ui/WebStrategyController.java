package login_ui;

import java.util.Iterator;

import VO.OrderVO;
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
import order_bl_serv.OrderBlServ;
import order_bl_servlmpl.OrderBlServImpl;

public class WebStrategyController {
	@FXML
	private AnchorPane OrderTable;
	
	@FXML
	private Button searchbutton;
	
	@FXML
	private Button checkbutton;
	
	@FXML
	private Button undobutton;


	@FXML
	private Button abnormalbutton;
	
	@FXML
	private TextField idfield;
	
	
	private static MainApp mainApp;
	
	private OrderBlServ orderBlServ=new OrderBlServImpl();
	
	private Iterator<OrderVO> orderList;
	
	public static void setUp(MainApp mainApp){

		WebStrategyController.mainApp=mainApp;
	}
	
		
	@FXML
	public void initialize(){
		OrderTable.setVisible(true);
	}
	

	
	@FXML 
	public void undo(){
		orderList = orderBlServ.getAllNotInOrderList();
		OrderTableController.setData(orderList);
	}
	
	
	
	@FXML
	public void abnormal(){
		orderList = orderBlServ.getAllAbnormalOrderList();
		OrderTableController.setData(orderList);
	}
	
	
	
}
