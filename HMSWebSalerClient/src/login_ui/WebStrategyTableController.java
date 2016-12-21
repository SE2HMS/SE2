package login_ui;

import java.util.Iterator;

import VO.CBDStrategy;
import VO.DoubleElevenStrategy;
import VO.StrategyVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import strategy_bl_serv.WebStrategyBlServ;
import strategy_bl_servlmpl.WebStrategyBlServlmpl;

public class WebStrategyTableController {
	@FXML
	private Button checkButton;
	@FXML
	private Button delButton;
	@FXML
	private TableView<WebStrategy> table;
	
	@FXML
	private TableColumn<WebStrategy,String> typecol;
	
	@FXML
	private TableColumn<WebStrategy,String> namecol;
	

	
	private static final ObservableList<WebStrategy> data = FXCollections.observableArrayList(); 
	

	
	private static MainApp mainApp;
	private WebStrategyBlServ serv = new WebStrategyBlServlmpl();
	public static void setUp(MainApp mainApp){
		
		WebStrategyTableController.mainApp=mainApp;
	}
	
	public static void setData(Iterator<StrategyVO> strategyVO){
		DoubleElevenStrategy des = null;
		CBDStrategy cbds = null;
		data.removeAll(data);
		if(strategyVO!=null)
			while(strategyVO.hasNext()){
				StrategyVO vo = strategyVO.next();
				if(vo.getType().equals("date")){
					des = (DoubleElevenStrategy)vo;
					data.add(new WebStrategy(des.getType(), des.getName()));
				}else if(vo.getType().equals("CBD")){
					cbds = (CBDStrategy)vo;
					data.add(new WebStrategy(cbds.getType(), cbds.getName()));
				}
			}
	}
	
	@FXML
	public void initialize(){

//		table.setItems(data);
//		typecol.setCellValueFactory(cellData->cellData.getValue().typeProperty());
//		namecol.setCellValueFactory(cellData->cellData.getValue().nameProperty());

		
	}
	
	@FXML
	public void check(){
//		if(table.getSelectionModel().getSelectedItem()!=null){
//			if(table.getSelectionModel().getSelectedItem().equals("date")){
//				mainApp.showWebStrategyDateDetail(table.getSelectionModel().getSelectedItem().getName());
//			}else{
//				mainApp.showWebStrategyCBDDetail(table.getSelectionModel().getSelectedItem().getName());
//			}
//			
//		}
//			
//		else{
//			Alert alert = new Alert(AlertType.INFORMATION);
//	        alert.setTitle("Message");
//	        alert.setHeaderText("错误");
//	        alert.setContentText("请选择一条策略！");
//	        alert.showAndWait();
//		}
	}
	
	@FXML
	public void delete(){
		String strategyName = table.getSelectionModel().getSelectedItem().getName();
		boolean success = serv.delStrategy(strategyName);
		if(success){
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Message");
	        alert.setHeaderText("成功");
	        alert.setContentText("删除促销策略成功！");
	        alert.showAndWait();
		}
	}
}
