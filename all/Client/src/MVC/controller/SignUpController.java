package MVC.controller;

import MVC.BeanController;
import MVC.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.util.ResourceBundle;


public class SignUpController {

    private Main main;
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private Button B_signUp;

    @FXML
    private void signUp() {
        //kontrola spravnosti vyplnenych policok
        if (login.getText().length() == 0 || password.getText().length() == 0 || password2.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("wrongLogin"));
            alert.showAndWait();
            return;
        }

        if(!(password.getText().equals(password2.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("passwordSame"));
            alert.showAndWait();
            return;
        }

        String name = login.getText();
        String password = login.getText();
        User user = new User(name,password);

        //pokusime sa usera zaregistrovat
        boolean success = false;
        try {
            success = BeanController.signUpUser(user);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(!success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("sameName"));
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("registration"));
            alert.showAndWait();
            main.getSecondaryStage().close();
            return;

        }

    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
