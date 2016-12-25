package login_ui;

import java.util.Iterator;

import VO.OrderVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import login_ui.MainApp;
import order_bl_serv.OrderBlServ;
import order_bl_servlmpl.OrderBlServImpl;

public class OrderInfoController {
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
		OrderInfoController.mainApp=mainApp;
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
	
	@FXML
	public void search(){
		if(orderBlServ.getOrderInfo(idfield.getText())!=null)
			mainApp.showOrderDetailsUI(idfield.getText());
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Message");
	        alert.setHeaderText("错误");
	        alert.setContentText("找不到该ID的订单！");
	        alert.showAndWait();
		}
			
	}

}
