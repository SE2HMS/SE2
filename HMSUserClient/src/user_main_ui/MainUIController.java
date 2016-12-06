package user_main_ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import login_ui.MainApp;

public class MainUIController {

/*
 * just for test
 * */
	@FXML
	private Label idLabel;  //id
	
	@FXML
	private Label userNameLabel; //鐢ㄦ埛鍚�
	
	@FXML
	private Label nameLabel; //濮撳悕
	
	
	@FXML
	private Label birthdayLabel; //鍑虹敓鏃ユ湡
	
	@FXML
	private Label BusiLabel;  //浼佷笟鍚嶇О
	
	@FXML
	private Label phoneLabel;  //鎵嬫満鍙�
	
	@FXML
	private Label memberLevelLabel; //浼氬憳绛夌骇
	

	
	
//	//鐢ㄦ埛涓荤晫闈㈢殑鍙�夐」锛岄椤碉紝涓汉璧勬枡锛屼俊鐢ㄨ褰曪紝璁㈠崟淇℃伅锛岃繃寰�閰掑簵淇℃伅
//	private ObservableList<String> options = FXCollections.observableArrayList("棣栭〉",
//			"涓汉璧勬枡","淇＄敤璁板綍","璁㈠崟淇℃伅","杩囧線閰掑簵淇℃伅");
//	
//	@FXML
//	private ListView<String> listView = new ListView<>(options);

    // Reference to the main application.
    private MainApp mainApp;
    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	public void initialize(){
//		listView.setItems(options);

	}
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
//        listView.setItems(options);
//        listView.getSelectionModel().get
    }
    
    @FXML
    public void starone(){
    	System.out.println("1");
    }
    
    @FXML
    public void startwo(){
    	
    }
    
    @FXML
    public void starthree(){
    	
    }
    
    @FXML
    public void starfour(){
    	
    }
    
    @FXML
    public void starfive(){
    	
    }
    
    @FXML
    public void scoreone(){
    	System.out.println("1");
    }
    
    @FXML
    public void scoretwo(){
    	
    }
    
    @FXML
    public void scorethree(){
    	
    }
    
    @FXML
    public void scorefour(){
    	
    }
    
    @FXML
    public void scorefive(){
    	
    }


}
