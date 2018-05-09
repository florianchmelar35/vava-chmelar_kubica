package MVC.controller;

import MVC.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Event;
import model.Group;

import java.util.List;
import java.util.ResourceBundle;

public class MakeGroupController {
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");
    private Main main;
    private MainController mainController;

    @FXML
    private Button B_make;

    @FXML
    private TextField T_name;

    @FXML
    private void create() {
        String name = T_name.getText();
        int idUser = main.getUser().getId();

        Group g = new Group(-1, name, idUser, false);
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
