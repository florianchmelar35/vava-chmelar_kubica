package MVC.controller.window;

import MVC.Main;
import MVC.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;

/**trieda spusta nove okno main a nastavujeme mu potrebne parametre*/
public class WindMain extends Stage {
    public WindMain(ResourceBundle multiLang, Main main) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/main.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.setTitle(multiLang.getString("mainWindow"));
        primaryStage.show();

        //prepnutie okien
        main.getPrimaryStage().close();
        main.setPrimaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        MainController mainController = loader.getController();
        mainController.setMain(main);
        mainController.initialization();
        mainController.addListener();
    }
}
