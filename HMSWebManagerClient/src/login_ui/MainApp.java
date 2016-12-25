package login_ui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import login_bl_serv.LoginBlServ;
import rmi.RemoteRunner;


public class MainApp extends Application {

    private Stage primaryStage, diaStage;
    private String currentId;
    private String currentUserName;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel Management System");
        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(event -> logout());
        this.diaStage = new Stage();
        showLoginUI();
    }

    private void logout() {
        LoginBlServ.getInstance().logout(currentId);
    }

    public void setCurrentUserName(String name) {
        this.currentUserName = name;
    }

    public void setCurrentId(String id) {
        this.currentId = id;
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

    /**
     * 显示主页
     */
    public void showWebManagerMain() {
        try {
            HotelStaffManageController.setMainApp(this);
            WebSalerManageController.setMainApp(this);
            MemberManageController.setMainApp(this);
            MainController controller = (MainController) replaceSceneContent("Main.fxml");
            controller.setMainApp(this);
            controller.setUserName(currentUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userID 要编辑信息的会员的id
     */
    public void editMemberInfo(String userID) {
        EditMemberInfoDialogController controller;
        EditMemberInfoDialogController.setID(userID);
        try {
            // Set the person into the controller.
            controller = (EditMemberInfoDialogController) replaceDiaSceneContent("EditMemberInfoDialog.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAddHotelStaffDialog() {
        AddHotelStaffDialogController controller;
        try {
            // Set the person into the controller.
            controller = (AddHotelStaffDialogController) replaceDiaSceneContent("AddHotelStaffDialog.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editHotelStaff(String id) {
        if (id == null || id.equals("")) {
            return;
        }
        EditHotelStaffInfoController controller;
        try {
            controller = (EditHotelStaffInfoController) replaceDiaSceneContent("EditHotelStaff.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
            controller.setID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAddWebSalerDialog() { //括号内应为WebSaler saler
        AddWebSalerDialogController controller;
        try {
            // Set the person into the controller.
            controller = (AddWebSalerDialogController) replaceDiaSceneContent("AddWebSalerDialog.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editWebSaler(String id) {
        if (id == null || id.equals("")) {
            return;
        }
        EditWebSalerInfoController.setID(id);
        EditWebSalerInfoController controller;
        try {
            controller = (EditWebSalerInfoController) replaceDiaSceneContent("EditWebSalerInfo.fxml");
            controller.setDialogStage(this.diaStage);
            controller.setMainApp(this);
//            controller.setID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * 把scene换掉，返回controller
     *
     * @param fxml
     * @return
     * @throws IOException
     */
    private Initializable replaceSceneContent(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        page = loader.load(in);
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.show();
        return (Initializable) loader.getController();
    }

    private Initializable replaceDiaSceneContent(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        page = loader.load(in);
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
