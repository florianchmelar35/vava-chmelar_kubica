package MVC.controller;

import MVC.Main;
import MVC.controller.window.WindAddEvent;
import MVC.controller.window.WindListGroup;
import MVC.controller.window.WindMakeGroup;
import MVC.controller.window.WindAddGroup;
import MVC.controller.window.WindLogin;
import MVC.model.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.ResourceBundle;

public class MainController {
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");
    private Main main;

    @FXML
    private Button B_make;
    @FXML
    private Button B_refresh;
    @FXML
    private Button B_list;
    @FXML
    private Label L_groupSetting;
    @FXML
    private Button B_add;
    @FXML
    private Button B_logOut;
    @FXML
    private ChoiceBox<String> Ch_events;
    @FXML
    private Button B_filter;
    @FXML
    private Button B_addEvent;
    @FXML
    private Button B_deleteEvent;
    @FXML
    //TODO
    private TableView<Event> T_events;

    @FXML
    void addEvent() {
        try {
            new WindAddEvent(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void addGroup() {
        try {
            new WindAddGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void makeGroup() {
        try {
            new WindMakeGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listGroup() {
        try {
            new WindListGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void logout() {
        try {
            new WindLogin(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
