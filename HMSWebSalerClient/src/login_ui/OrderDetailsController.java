package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import VO.OrderState;
import VO.OrderVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private Button revokebutton;
	
	@FXML 
	private Button commentbutton;
	
	@FXML
	private TableView<RoomTable> table;
	
	@FXML
	private TableColumn<RoomTable,String> roomtypecol;
	
	@FXML
	private TableColumn<RoomTable,String> roomnumcol;
	
	@FXML
	private TableColumn<RoomTable,String> pricecol;
	
	@FXML
	private TableColumn<RoomTable,String> subtotelcol;
	
	private OrderBlServ orderBlServ=new OrderBlServImpl();
	
	private final ObservableList<RoomTable> data = FXCollections.observableArrayList(); 
	
	

	public void setDialogStage(Stage diaStage) {
		// TODO Auto-generated method stub
		this.stage = diaStage;
	}

	public static void setUp(MainApp mainApp,String orderID) {
		// TODO Auto-generated method stub
		OrderDetailsController.orderID=orderID;
		OrderDetailsController.mainApp=mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		revokebutton.setDisable(true);

		OrderVO o=orderBlServ.getOrderInfo(orderID);
		
		if(o.getState().equals(OrderState.WAITING))
			revokebutton.setDisable(false);
		if(o.getState().equals(OrderState.FINISH))
			commentbutton.setDisable(false);
		if(o.getState().equals(OrderState.REVOKE))
			changeablelabel.setText("����ʱ��");
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
			subtotelcol.setCellValueFactory(cellData->cellData.getValue().subtotelProperty());
	}
	
	@FXML
	public void revoke(){
		
	}
	
	@FXML
	public void resumeHalf(){
		
	}
	
	@FXML
	public void resumrAll(){
		
	}

}
