package login_ui;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import strategy_bl_serv.WebStrategyBlServ;
import strategy_bl_servlmpl.WebStrategyBlServlmpl;

public class StrategyCBDEditController implements Initializable{

	@FXML
	TextField nameField;
	@FXML
	TextField CBDField;
	
	@FXML
	TextField discount1Field;
	@FXML
	TextField discount2Field;
	@FXML
	TextField discount3Field;
	
	@FXML
	Button confirm;
	@FXML
	Button cancel;
	
	
	private Stage dialogStage;
	private MainApp mainApp;
	private WebStrategyBlServ serv= new WebStrategyBlServlmpl();
	private boolean okClicked = false;
	
	
	@FXML
	public void handleOK(){
		if(isInputValid()){
			String strategyName = nameField.getText();
			String CBD = CBDField.getText();
			double lev0 = Double.parseDouble(discount1Field.getText());
			double lev1 = Double.parseDouble(discount2Field.getText());
			double lev2 = Double.parseDouble(discount3Field.getText());
			
		    boolean success = serv.addCBDStrategy(strategyName, lev0, lev1, lev2, CBD);
		    if(success){
		    	Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("成功！");
                alert.setContentText("编辑网站促销策略成功！");
                alert.showAndWait();
                okClicked = true;
                dialogStage.close();
		    }else{
		    	Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Message");
                alert.setHeaderText("错误");
                alert.setContentText("促销策略已存在！");
                alert.showAndWait();
		    }   
		}
	}
	
	/**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
    	
        return okClicked;
    }
	public void setMainApp(MainApp mainApp){
    	this.mainApp = mainApp;
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    

    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (CBDField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid CBD!\n"; 
        }
        if (discount1Field.getText() == null || discount1Field.getText().length() == 0) {
            errorMessage += "No valid level1 discount!\n"; 
        }
        if (discount2Field.getText() == null || discount2Field.getText().length() == 0) {
            errorMessage += "No valid level2 discount!\n"; 
        }
        if (discount3Field.getText() == null || discount3Field.getText().length() == 0) {
            errorMessage += "No valid level3 discount!\n"; 
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Message");
            alert.setHeaderText("请将新增信息填写完整！");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
    
	@FXML 
	public void giveup(){
		this.dialogStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
