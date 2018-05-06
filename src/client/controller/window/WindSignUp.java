package client.controller.window;

import client.Main;
import client.controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class WindSignUp {
    public WindSignUp(ResourceBundle multiLang, Main main) throws Exception {
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/view/signUp.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 505, 255));
        primaryStage.setTitle(multiLang.getString("signUp"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        //main.setPrimaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        SignUpController signUpController = loader.getController();
        signUpController.setMain(main);
    }
}
