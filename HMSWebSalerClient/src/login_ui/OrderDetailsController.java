package login_ui;

import java.net.URL;
import java.util.AbstractCollection;
import java.util.ResourceBundle;

import VO.OrderState;
import VO.OrderVO;
import VO.UserInOrder;
import VO.UserVO;
import credit_bl_serv.CreditBlServ;
import credit_bl_servlpml.CreditBlServImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import login_ui.MainApp;
import order_bl_serv.OrderBlServ;
import order_bl_servlmpl.OrderBlServImpl;

public class OrderDetailsController implements Initializable{
	
	private static String userID;
	
	private static String orderID;
	
	private static MainApp mainApp;
	
	@FXML
	private Stage stage;
	@FXML
	private Label orderidlabel;
	
	@FXML
	private Label usernamelabel;
	
	@FXML
	private Label hotelnamelabel;
	
	@FXML
	private Label intimelabel;
	
	@FXML
	private Label outtimelabel;
	
	@FXML
	private Label changeablelabel;
	
	@FXML
	private Label lasttimelabel;
	
	@FXML
	private Label orderstatelabel;
	

	@FXML
	private Label totallabel;
	
	@FXML
	private Button halfButton;
	@FXML
	private Button allButton;
	@FXML
	private Button closeButton;
	@FXML
	private TableView<RoomTable> table;
	
	@FXML
	private TableColumn<RoomTable,String> roomtypecol;
	
	@FXML
	private TableColumn<RoomTable,String> roomnumcol;
	
	@FXML
	private TableColumn<RoomTable,String> pricecol;
	
	@FXML
	private TableColumn<RoomTable,String> subtotalcol;
	
	private OrderBlServ orderBlServ = new OrderBlServImpl();

	private final ObservableList<RoomTable> data = FXCollections.observableArrayList(); 
	OrderVO o;
	UserInOrder user;

	public void setDialogStage(Stage diaStage) {
		this.stage = diaStage;
	}

	public static void setUp(MainApp mainApp,String orderID) {
		OrderDetailsController.orderID=orderID;
		OrderDetailsController.mainApp=mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		o=orderBlServ.getOrderInfo(orderID);
		user = o.getUser();
		userID = user.getName();
		halfButton.setDisable(true);
		allButton.setDisable(true);
		if(o.getState().equals(OrderState.ABNORMAL)) {
			halfButton.setDisable(false);
			allButton.setDisable(false);
		}
		if(o.getState().equals(OrderState.REVOKE)) {
			changeablelabel.setText("订单撤销时间");
		}
			orderidlabel.setText(o.getId());
			usernamelabel.setText(o.getUser().getName());
			hotelnamelabel.setText(o.getHotel().getHotelName());
			intimelabel.setText(o.getInTime().toString());
			outtimelabel.setText(o.getOutTime().toString());
			lasttimelabel.setText(o.getExecTime().toString());
			orderstatelabel.setText(o.getState().toString());
			totallabel.setText(String.valueOf(o.getTotal()));
		
			table.setItems(data);
			roomtypecol.setCellValueFactory(cellData->cellData.getValue().roomtypeProperty());
			roomnumcol.setCellValueFactory(cellData->cellData.getValue().ordernumProperty());
			pricecol.setCellValueFactory(cellData->cellData.getValue().priceProperty());
			subtotalcol.setCellValueFactory(cellData->cellData.getValue().subtotelProperty());
	}
	
	@FXML
	public void giveup(){
		this.stage.close();
	}
	
	@FXML
	public void resumeHalf(){
		boolean success = orderBlServ.revokeExceptionOrder(orderID, false);
		if(success) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.stage);
			alert.setContentText("恢复成功");
			alert.showAndWait();
			mainApp.showWebSalerMain();
			mainApp.controller.showOrderCheckPane();
			this.stage.close();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.stage);
			alert.setContentText("发生了一些异常，请稍后重试");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void resumeAll(){
		boolean success = orderBlServ.revokeExceptionOrder(orderID, true);
		if(success) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.stage);
			alert.setContentText("恢复成功");
			alert.showAndWait();
			mainApp.showWebSalerMain();
			mainApp.controller.showOrderCheckPane();
			this.stage.close();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.stage);
			alert.setContentText("发生了一些异常，请稍后重试");
			alert.showAndWait();
		}
	}

}
