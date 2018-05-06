package MVC.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class WindSignUp {
    public WindSignUp(ResourceBundle multiLang) throws Exception {
        super();
        Stage primaryStage=new Stage();
        Parent okno = FXMLLoader.load(getClass().getClassLoader().getResource("client/view/signUp.fxml"), multiLang);
        primaryStage.setTitle(multiLang.getString("signUp"));
        primaryStage.setScene(new Scene(okno, 380, 230));
        primaryStage.show();
    }
}
