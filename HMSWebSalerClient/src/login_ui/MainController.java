package login_ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class MainController implements Initializable {

    @FXML
    private Button creditRechargeButton;

    @FXML
    private Button orderCheckButton;

    @FXML
    private Button webStrategyButton;

    @FXML
    private AnchorPane creditRechargePane;

    @FXML
    private AnchorPane orderCheckPane;

    @FXML
    private AnchorPane webStrategyPane;

    //Reference to the main application
    private MainApp mainApp;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        creditRechargePane.setVisible(true);
    }

    /**
     * 界面跳转
     */
    @FXML
    public void showCreditRechargePane() {
        orderCheckPane.setVisible(false);
        webStrategyPane.setVisible(false);
        creditRechargePane.setVisible(true);
    }

    @FXML
    public void showorderCheckPane() {
        creditRechargePane.setVisible(false);
        webStrategyPane.setVisible(false);
        orderCheckPane.setVisible(true);
    }

    @FXML
    public void showWebStrategyPane() {
        creditRechargePane.setVisible(false);
        orderCheckPane.setVisible(false);
        webStrategyPane.setVisible(true);

    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }


}
