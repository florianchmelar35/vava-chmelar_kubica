package MVC.controller;

import MVC.BeanController;
import MVC.Main;
import MVC.model.GroupProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Group;

import java.util.ResourceBundle;
import java.util.logging.Level;

public class ListGroupController {
    private Main main;
    private MainController mainController;
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");

    @FXML
    private TableView<GroupProperty> T_groups;
    @FXML
    private TableColumn<GroupProperty, String> TC_name;
    @FXML
    private TableColumn<GroupProperty, Integer> TC_idUser;
    @FXML
    private TableColumn<GroupProperty, Integer> TC_id;
    @FXML
    private Button B_leave;
    @FXML
    private Button B_delete;

    public void initialization() {
        TC_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TC_idUser.setCellValueFactory(cellData -> cellData.getValue().idUserProperty().asObject());
        TC_id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        T_groups.setItems(main.getGroupsAll());
    }

    @FXML
    private void leave() {
        GroupProperty gp = T_groups.getSelectionModel().selectedItemProperty().getValue();

        if(gp.getIdUser() == main.getUser().getId()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("cantLeave"));
            alert.showAndWait();
            return;
        }

        boolean success = false;
        try {
            success = BeanController.leaveGroup(main.getUser().getId(), gp.getId());
        } catch(Exception e) {
            main.getLog().log(Level.SEVERE, "leave Group");
            e.printStackTrace();
        }

        if(!success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("leaveNot"));
            alert.showAndWait();
            return;
        }

        for(Group g : main.getGroupList()) {
            if(g.getId() == gp.getId()) {
                main.getGroupList().remove(g);
                main.getGroupsAll().remove(gp);
                initialization();
                mainController.refresh();
                break;
            }
        }

    }

    @FXML
    private void delete() {
        GroupProperty gp = T_groups.getSelectionModel().selectedItemProperty().getValue();

        if(gp == null)
            return;

        if(gp.getIdUser() != main.getUser().getId()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("dontOwn"));
            alert.showAndWait();
            return;
        }

        for(Group g : main.getGroupList()) {
            if(g.getId() == gp.getId()) {
                if(g.isPersonal()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(multiLang.getString("information"));
                    alert.setHeaderText(null);
                    alert.setContentText(multiLang.getString("personal"));
                    alert.showAndWait();
                    return;
                }
                main.getDeleted().add(g.getId());
                main.getGroupList().remove(g);
                main.getGroupsAll().remove(gp);
                initialization();
                mainController.refresh();
                break;
            }
        }


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
