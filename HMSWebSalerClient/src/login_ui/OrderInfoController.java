package login_ui;

import java.util.Iterator;

import VO.OrderVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	private Button allbutton;
	
	@FXML
	private Button undobutton;
	
	@FXML
	private Button dobutton;
	
	@FXML
	private Button revokebutton;
	
	@FXML
	private Button abnormalbutton;
	
	@FXML
	private TextField idfield;
	
	private static String id;
	
	private static MainApp mainApp;
	
	private OrderBlServ orderBlServ=new OrderBlServImpl();
	
	private Iterator<OrderVO> orderList;
	
	public static void setUp(String id,MainApp mainApp){
		OrderInfoController.id=id;
		OrderInfoController.mainApp=mainApp;
	}
	
		
	@FXML
	public void initialize(){
		OrderTable.setVisible(true);
	}
	
	@FXML
	public void all(){
		orderList=orderBlServ.getOrderList(id);
		OrderTableController.setData(orderList);
	}
	
	@FXML 
	public void undo(){
		orderList=orderBlServ.getNotInOrderList(id);
		OrderTableController.setData(orderList);
	}
	
	@FXML
	public void hasdone(){
		orderList=orderBlServ.getFinishOrderList(id);
		OrderTableController.setData(orderList);
	}
	
	@FXML
	public void revoke(){
		orderList=orderBlServ.getRevokeOrderList(id);
		OrderTableController.setData(orderList);
	}
	
	@FXML
	public void abnormal(){
		orderList=orderBlServ.getAbnormalOrderList(id);
		OrderTableController.setData(orderList);
	}
	
	@FXML
	public void search(){
		
		if(orderBlServ.getOrderInfo(idfield.getText())!=null)
			mainApp.showOrderDetailsUI(idfield.getText());
		else
			mainApp.showErrorMessage("���������ڣ�");
	}

}
