package MVC.controller;

import MVC.Main;
import MVC.model.GroupProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddEventController {
    private Main main;
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
        CH_groups.setItems(main.getGroups());
    }

    @FXML
    public void add() {
        String name = T_name.getText();
        String place = T_place.getText();
        LocalDate date = DP_date.getValue();

        String hour = T_hour.getText();
        String minute = T_minute.getText();

        try {
            int h = Integer.parseInt(hour);
            int m = Integer.parseInt(minute);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("badInformation2"));
            alert.showAndWait();
            return;
        }

        String comment = T_comment.getText();
        int idGroup = CH_groups.getValue().getId();


    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
