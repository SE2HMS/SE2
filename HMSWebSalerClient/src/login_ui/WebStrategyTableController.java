package login_ui;

import java.util.Iterator;

import VO.CBDStrategy;
import VO.DoubleElevenStrategy;
import VO.OrderVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import order_bl_serv.OrderBlServ;
import order_bl_servlmpl.OrderBlServImpl;

public class WebStrategyTableController {
	@FXML
	private Button checkButton;
	@FXML
	private TableView<WebStrategyTable> table;
	
	@FXML
	private TableColumn<WebStrategyTable,String> typecol;
	
	@FXML
	private TableColumn<WebStrategyTable,String> namecol;
	
	
	
	private Iterator<DoubleElevenStrategy> dateStrategy;
	private Iterator<CBDStrategy> cbdStrategy;
	
	
	private static final ObservableList<WebStrategyTable> data = FXCollections.observableArrayList(); 
	

	
	private static MainApp mainApp;
	
	public static void setUp(MainApp mainApp){
		
		WebStrategyTableController.mainApp=mainApp;
	}
	
	public static void setData(Iterator<DoubleElevenStrategy> dateStrategy,
			Iterator<CBDStrategy> cbdStrategy){
		data.removeAll(data);
		if(dateStrategy!=null)
			while(dateStrategy.hasNext()){
				DoubleElevenStrategy des = dateStrategy.next();
				data.add(new WebStrategyTable(des.getType(), des.getName()));
			}
		
		if(cbdStrategy!=null)
			while(cbdStrategy.hasNext()){
				CBDStrategy cbds = cbdStrategy.next();
				data.add(new WebStrategyTable(cbds.getType(), cbds.getName()));
			}
	}
	
	@FXML
	public void initialize(){
//		orderList=orderBlServ.getAllNotInOrderList();
//		while(orderList.hasNext()){
//			OrderVO o=orderList.next();
//			data.add(new OrderTable(o.getId(),o.getHotel().getHotelName(),o.getInTime().toString(),
//					o.getOutTime().toString(),o.getExecTime().toString()
//					,String.valueOf(o.getTotal()),o.getState().toString()));
//		}
//		table.setItems(data);
//		idcol.setCellValueFactory(cellData->cellData.getValue().idProperty());
//		hotelnamecol.setCellValueFactory(cellData->cellData.getValue().hotelnameProperty());
//		intimecol.setCellValueFactory(cellData->cellData.getValue().inttimeProperty());
//		outtimecol.setCellValueFactory(cellData->cellData.getValue().outimeProperty());
//		lasttimecol.setCellValueFactory(cellData->cellData.getValue().lasttimeProperty());
//		totalcol.setCellValueFactory(cellData->cellData.getValue().totelProperty());
//		statecol.setCellValueFactory(cellData->cellData.getValue().stateProperty());
		
	}
	
	@FXML
	public void check(){
//		if(table.getSelectionModel().getSelectedItem()!=null)
//			mainApp.showOrderDetailsUI(table.getSelectionModel().getSelectedItem().getOrderID());
//		else{
//			Alert alert = new Alert(AlertType.INFORMATION);
//	        alert.setTitle("Message");
//	        alert.setHeaderText("错误");
//	        alert.setContentText("请选择一条订单！");
//	        alert.showAndWait();
//		}
	}
}
