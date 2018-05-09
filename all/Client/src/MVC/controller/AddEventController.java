package MVC.controller;

import MVC.Main;
import MVC.model.EventProperty;
import MVC.model.GroupProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Event;
import model.Group;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;


public class AddEventController {
    private Main main;
    private MainController mainController;
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");

    @FXML
    private TextField T_name;
    @FXML
    private TextField T_place;
    @FXML
    private DatePicker DP_date;
    @FXML
    private TextField T_hour;
    @FXML
    private TextField T_minute;
    @FXML
    private TextArea T_comment;
    @FXML
    private Button B_add;
    @FXML
    private ChoiceBox<GroupProperty> CH_groups;

    //nastavujem do choiceboxu groupy
    public void initialization() {
        CH_groups.setItems(main.getGroupsMy());
        CH_groups.getSelectionModel().selectFirst();
    }

    //z policok ziskavam udaje z ktorych vytvorim event
    //ten nasledne pridam do hlavneho listu groupList
    //okno sa zatvori
    @FXML
    public void add() {
        String name = T_name.getText();
        String place = T_place.getText();
        LocalDate date = DP_date.getValue();
        String hour = T_hour.getText();
        String minute = T_minute.getText();
        int h;
        int m;
        try {
            h = Integer.parseInt(hour);
            m = Integer.parseInt(minute);
            if (h>23 || h<0 || m>59 || m<0)
                throw new Exception();
        } catch(Exception e) {
            main.getLog().log(Level.FINE, "uncorrect fill");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("badInformation2"));
            alert.showAndWait();
            return;
        }
        LocalTime time = LocalTime.of(h,m);
        String comment = T_comment.getText();

        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        Event e = new Event(name,comment,timestamp,place);
        //EventProperty ep = new EventProperty(name,comment,date,time,place);

        boolean test = false;
        int idGroup = CH_groups.getValue().getId();
        for(Group g : main.getGroupList()) {
            if (idGroup == g.getId()) {
                g.getEvents().add(e);
                test = true;
                break;
            }
        }

        if(!test){
            main.getLog().log(Level.FINER, "user is master");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(multiLang.getString("information"));
        alert.setHeaderText(null);
        alert.setContentText(multiLang.getString("addSuccess"));
        alert.showAndWait();

        mainController.refresh();
        main.getSecondaryStage().close();
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
