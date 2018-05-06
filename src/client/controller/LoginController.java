package client.controller;

import client.Main;
import client.controller.window.WindMain;
import client.controller.window.WindSignUp;
import client.model.Event;
import client.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    String langS = "EN";
    ResourceBundle multiLang = ResourceBundle.getBundle("multiLang");
    private Main main;

    @FXML
    private Label L_setData;
    @FXML
    private Label L_lang;
    @FXML
    private Label L_user;
    @FXML
    private Label L_pass;
    @FXML
    private ComboBox combo;
    @FXML
    private TextField T_user;
    @FXML
    private PasswordField T_pass;
    @FXML
    private Button B_signIn;
    @FXML
    private Button B_signUp;

    @FXML
    void mainWindow(){
        try {
            new WindMain(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void login(){
        if (T_user.getText().length() == 0 || T_pass.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("wrongLogin"));

            alert.showAndWait();
            return;
        }

        //User user = new User(T_user.getText(), T_pass.getText());
    }

    @FXML
    void signUp(){
        try {
            new WindSignUp(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void signIn() {
        try {
            new WindMain(multiLang, this.main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void lang(){
        if(combo.getValue() == null)
            langS = "EN";
        else
            langS = (String) combo.getValue();

        if(langS == "EN")
            Locale.setDefault(new Locale("en", "US"));
        else
            Locale.setDefault(new Locale("sk", "SK"));
        multiLang = ResourceBundle.getBundle("multiLang");

        L_setData.setText(multiLang.getString("setLoginData"));
        L_lang.setText(multiLang.getString("changeLang"));
        L_pass.setText(multiLang.getString("password"));
        L_user.setText(multiLang.getString("userName"));
        B_signIn.setText(multiLang.getString("signIn"));
        B_signUp.setText(multiLang.getString("signUp"));

    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
