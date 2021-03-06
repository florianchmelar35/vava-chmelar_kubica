package MVC;

import MVC.model.EventProperty;
import MVC.model.GroupProperty;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Group;
import model.User;
import service.LogClass;
import service.LoginBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends Application {

    private Stage primaryStage;
    private Stage secondaryStage;

    private User user;
    private List<Group> groupList;
    private List<Integer> deleted = new ArrayList<Integer>();

    private ObservableList<EventProperty> events = FXCollections.observableArrayList();
    private ObservableList<GroupProperty> groupsAll = FXCollections.observableArrayList();
    private ObservableList<GroupProperty> groupsMy = FXCollections.observableArrayList();

    private Logger log;


    @Override
    public void start(Stage primaryStage) throws Exception{
        log = LogClassClient.getLog();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/login.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 505, 255));
        primaryStage.show();

        //controller ma v sebe ulozenu tuto instanciu MainApp classy, aby s nou mohol pracovat
        MVC.controller.LoginController loginController = loader.getController();
        loginController.setMain(this);
    }


    public static void main(String[] args) {

        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getSecondaryStage() {
        return secondaryStage;
    }

    public void setSecondaryStage(Stage secondaryStage) {
        this.secondaryStage = secondaryStage;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ObservableList<EventProperty> getEvents() {
        return events;
    }

    public void setEvents(ObservableList<EventProperty> events) {
        this.events = events;
    }

    public List<Integer> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<Integer> deleted) {
        this.deleted = deleted;
    }

    public ObservableList<GroupProperty> getGroupsAll() {
        return groupsAll;
    }

    public void setGroupsAll(ObservableList<GroupProperty> groupsAll) {
        this.groupsAll = groupsAll;
    }

    public ObservableList<GroupProperty> getGroupsMy() {
        return groupsMy;
    }

    public void setGroupsMy(ObservableList<GroupProperty> groupsMy) {
        this.groupsMy = groupsMy;
    }

    public Logger getLog() {
        return log;
    }
}
