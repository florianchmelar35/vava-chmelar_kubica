package MVC.controller;

import MVC.BeanController;
import MVC.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Group;

import java.util.ResourceBundle;

public class AddGroupController {
    private Main main;
    private MainController mainController;
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");

    @FXML
    private TextField T_id;
    @FXML
    private Button B_join;

    @FXML
    public void add() {
        int id;
        try {
            id = Integer.parseInt(T_id.getText());
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("badInformation2"));
            alert.showAndWait();
            return;
        }

        Group g = null;
        try {
            g = BeanController.addGroup(main.getUser().getId(), id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(g == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("badInformation3"));
            alert.showAndWait();
            return;
        }

        main.getGroupList().add(g);

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
