package MVC.controller.window;

import MVC.Main;
import MVC.controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno signUp a nastavujeme mu potrebne parametre*/
public class WindSignUp {
    public WindSignUp(ResourceBundle multiLang, Main main) throws Exception {
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/signUp.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 505, 255));
        primaryStage.setTitle(multiLang.getString("signUp"));
        primaryStage.show();

        //prepnutie okien
        main.setSecondaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        SignUpController signUpController = loader.getController();
        signUpController.setMain(main);
    }
}
