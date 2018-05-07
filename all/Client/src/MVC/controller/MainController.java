package MVC.controller;

import MVC.Main;
import MVC.controller.window.WindAddEvent;
import MVC.controller.window.WindListGroup;
import MVC.controller.window.WindMakeGroup;
import MVC.controller.window.WindAddGroup;
import MVC.controller.window.WindLogin;
import MVC.model.EventProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Event;
import model.Group;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController {
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");
    private Main main;

    @FXML
    private Label L_name;
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
    private TableView<EventProperty> T_events;

    private void initialization() {
        //meno pouzivatela
        L_name.setText(main.getUser().getLogin());

        //naplnam list eventov, ktory sa bude zobrazovat v tabulke
        for(Group g : main.getGroupList()) {
            for(Event e : g.getEvents()) {
               // LocalDate localDate = new LocalDate(e.getDate().getYear(), e.getDate().getDay(), e.getDate().getMonth());
               // String date =
                //EventProperty ep = new EventProperty(e.getName(), e.getComment(), e.getDate(), e.getPlace());
               // main.getEvents().add(ep);
            }
        }

        Timestamp t = new Timestamp(5000000);
        t.getTime();


    }

    @FXML
    private void addEvent() {
        try {
            new WindAddEvent(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void addGroup() {
        try {
            new WindAddGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void makeGroup() {
        try {
            new WindMakeGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void listGroup() {
        try {
            new WindListGroup(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
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
