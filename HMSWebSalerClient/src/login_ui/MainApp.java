package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import login_bl_serv.LoginBlServ;
import rmi.RemoteRunner;

import java.io.InputStream;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {

    private Stage primaryStage, diaStage;
    private String currentId, currentName;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Hotel Management System");
        this.primaryStage.setOnCloseRequest((event) -> logout(currentId));
        this.diaStage = new Stage();
        showLoginUI();
    }

    private void logout(String id) {
        LoginBlServ.getInstance().logout(currentId);
    }

    public void setCurrentId(String id) {
        currentId = id;
    }

    public void setCurrentName(String name) {
        currentName = name;
    }

    public String getCurrentName() {
        return currentName;
    }

    /**
     * 显示登录界面
     */
    public void showLoginUI() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setMainApp(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showWebSalerMain() {
        try {
            CreditRechargeController.setMainApp(this);
            OrderTableController.setUp(this);
            OrderInfoController.setUp(this);
            WebStrategyController.setUp(this);
            WebStrategyTableController.setUp(this);
            MainController controller = (MainController) replaceSceneContent("Main.fxml");
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCreditRecharge(String id) {
        CreditRechargeDialogController controller;
        CreditRechargeDialogController.setID(id);
        try {
            controller = (CreditRechargeDialogController) replaceDiaSceneContent("CreditRechargeDialog.fxml");
            controller.setDialogStage(diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOrderDetailsUI(String orderID) {
        OrderDetailsController controller;
        OrderDetailsController.setUp(this, orderID);
        try {
            controller = (OrderDetailsController) replaceDiaSceneContent("OrderDetailsUI.fxml");
            controller.setDialogStage(diaStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddWebStrategyDate() {
        StrategydateAddController controller;
        try {
            controller = (StrategydateAddController) replaceDiaSceneContent("StrategydateAdd.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWebStrategyDateDetail(String strategyName) {
        StrategydateEditController controller;
        try {
            controller = (StrategydateEditController) replaceDiaSceneContent("StrategydateEdit.fxml");
            controller.setup(diaStage, this, strategyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddWebStrategyCBD() {
        StrategyCBDAddController controller;
        try {
            controller = (StrategyCBDAddController) replaceDiaSceneContent("StrategyCBDAdd.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWebStrategyCBDDetail(String strategyName) {
        StrategyCBDEditController controller;
        try {
            controller = (StrategyCBDEditController) replaceDiaSceneContent("StrategyCBDEdit.fxml");
            controller.setup(diaStage, this, strategyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.show();
        return (Initializable) loader.getController();
    }

    private Initializable replaceDiaSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        diaStage.setScene(scene);
        diaStage.show();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        new RemoteRunner();
        launch(args);
    }
}
