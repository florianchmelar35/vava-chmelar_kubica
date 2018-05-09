package MVC.controller.window;

import MVC.Main;
import MVC.controller.AddEventController;
import MVC.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno addEvent a nastavujeme mu potrebne parametre*/
public class WindAddEvent extends Stage {
    public WindAddEvent(ResourceBundle multiLang, Main main, MainController mainController) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/addEvent.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle(multiLang.getString("addEvent"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        main.setSecondaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        AddEventController addEventController = loader.getController();
        addEventController.setMain(main);
        addEventController.setMainController(mainController);
        addEventController.initialization();
    }
}
