package MVC.controller.window;

import MVC.Main;
import MVC.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno login a nastavujeme mu potrebne parametre*/
public class WindLogin extends Stage {
    public WindLogin(ResourceBundle multiLang, Main main) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/login.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 505, 255));
        primaryStage.setTitle(multiLang.getString("login"));
        primaryStage.show();

        //prepnutie okien
        main.getPrimaryStage().close();
        main.setPrimaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        LoginController loginController = loader.getController();
        loginController.setMain(main);
    }
}
