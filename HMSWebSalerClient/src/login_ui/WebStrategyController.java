package login_ui;

import java.util.Iterator;

import VO.StrategyVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import strategy_bl_serv.WebStrategyBlServ;
import strategy_bl_servlmpl.WebStrategyBlServlmpl;

public class WebStrategyController {
    @FXML
    private AnchorPane webStrategyTable;

    @FXML
    private Button checkbutton;

    @FXML
    private Hyperlink addNewdate,addNewCBD,addNewLevel;

    private static MainApp mainApp;

    private WebStrategyBlServ strategyBlServ = new WebStrategyBlServlmpl();

    private Iterator<StrategyVO> strategyList;

    public static void setUp(MainApp mainApp) {
        WebStrategyController.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        webStrategyTable.setVisible(true);
        strategyList = strategyBlServ.getStrategy();
        WebStrategyTableController.setData(strategyList);
    }

    @FXML
    public void addNewLevelStrategy() {
        mainApp.addWebStrategyLevel();
    }

    @FXML
    public void addNewdateStrategy() {
        mainApp.addWebStrategyDate();
    }

    @FXML
    public void addNewCBDStrategy() {
        mainApp.addWebStrategyCBD();
    }
}
