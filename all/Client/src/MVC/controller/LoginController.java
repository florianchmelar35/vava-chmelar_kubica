package MVC.controller;

import MVC.BeanController;
import MVC.Main;
import MVC.controller.window.WindMain;
import MVC.controller.window.WindSignUp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Group;
import model.User;

import java.util.List;
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

    //otvorenie okna signUp
    @FXML
    private void signUp(){

        try {
            new WindSignUp(multiLang, this.main);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //prihlasenie sa usera, pricom prebieha komunikacia so serverom
    //ktorej ucelom je v prvom rade zistit, ci taky user existuje a
    //nasledne ziskat vsetky eventy usera
    @FXML
    private void signIn() {

        //ak su textove polia prazdne, nedeje sa nic
        if(!checkEmpty())
            return;

        String login = T_user.getText();
        String password = T_pass.getText();
        User user = new User(login, password);

        //kontrolujem meno usera a zaroven dostavam jeho id
        int idUser = -1;
        try {
            idUser = BeanController.checkUser(user);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        //ak user uz existuje, prichadza hodnota -1
        if(idUser < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("badInformation"));
            alert.showAndWait();
            return;
        }

        //nastavujem id a ukladam usera do Mainu
        user.setId(idUser);
        main.setUser(user);

        //prichadza list groups, ktory obsahuje vsetky eventy usera, roztriedene podla group
        List<Group> groupList = null;
        try {
            groupList = BeanController.getData(user.getId());
        } catch(Exception e) {
            e.printStackTrace();
        }

        main.setGroupList(groupList);

        try {
            new WindMain(multiLang, this.main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //nastavovanie jazyka, ktory je nasledne pouzivany v celom programe
    @FXML
    private void lang(){
        if(combo.getValue() == null)
            langS = "EN";
        else
            langS = (String) combo.getValue();

        if(langS.equals("EN"))
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

    private boolean checkEmpty(){
        if (T_user.getText().length() == 0 || T_pass.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(multiLang.getString("information"));
            alert.setHeaderText(null);
            alert.setContentText(multiLang.getString("wrongLogin"));
            alert.showAndWait();
            return false;
        }

        return true;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
