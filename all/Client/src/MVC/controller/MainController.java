package MVC.controller;

import MVC.BeanController;
import MVC.Main;
import MVC.controller.window.WindAddEvent;
import MVC.controller.window.WindListGroup;
import MVC.controller.window.WindMakeGroup;
import MVC.controller.window.WindAddGroup;
import MVC.controller.window.WindLogin;
import MVC.model.EventProperty;
import MVC.model.GroupProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Event;
import model.Group;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    private TableView<EventProperty> T_events;
    @FXML
    private TableColumn<EventProperty, String> TC_name;
    @FXML
    private TableColumn<EventProperty, String> TC_place;
    @FXML
    private TableColumn<EventProperty, LocalDate> TC_date;
    @FXML
    private TableColumn<EventProperty, LocalTime> TC_time;
    @FXML
    private TableColumn<EventProperty, String> TC_comment;

    //rozpracovanie listu, ktory mame k dispozicii z classy main
    //dalej mapuje potrebne veci do tabulky
    public void initialization() {
        //meno pouzivatela
        L_name.setText(main.getUser().getLogin());

        //naplnam list eventov, ktory sa bude zobrazovat v tabulke
        //rovnako naplnam aj tabulku group
        for(Group g : main.getGroupList()) {
            GroupProperty gp = new GroupProperty(g.getId(),g.getName(),g.getIdUser());
            main.getGroups().add(gp);
            

            for(Event e : g.getEvents()) {
               int year = e.getDate().getYear();
               int month = e.getDate().getMonth();
               int day = e.getDate().getDay();
               LocalDate date = LocalDate.of(year,month,day);

               int hours = e.getDate().getHours();
               int minutes = e.getDate().getHours();
               LocalTime time = LocalTime.of(hours,minutes);

               EventProperty ep = new EventProperty(e.getName(), e.getComment(), date, time, e.getPlace());
               main.getEvents().add(ep);
            }
        }

        //namapovanie zobrazovacej tabulky
        TC_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TC_place.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
        TC_date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TC_time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        TC_comment.setCellValueFactory(cellData -> cellData.getValue().commentProperty());

        T_events.setItems(main.getEvents());


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

    //pri odhlaseni posielam vsetky rozne listy a prichadza mi
    //naspat boolean hodnota, ktora je kladna ak komunikacia prebehla
    //uspesne
    @FXML
    private void logout() {
        boolean success = false;
        try {
            success = BeanController.logout(main.getUser(), main.getGroupList(), main.getDeleted(), main.getCreated());
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(!success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("logOutNotSuccess"));
            alert.showAndWait();
            return;
        }

        try {
            new WindLogin(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //pri refreshnuti posielam server vsetky listy a naspat mi
    //prichadza jeden list, ktory opat rozpracovavam pomocou
    //metody initialization
    @FXML
    private void refresh() {
        List<Group> list = null;
        try {
            list = BeanController.refresh(main.getUser(), main.getGroupList(), main.getDeleted(), main.getCreated());
        } catch(Exception e) {
            e.printStackTrace();
        }

        main.setGroupList(list);
        main.setEvents(null);

        this.initialization();
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
