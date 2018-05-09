package MVC.controller.window;

import MVC.Main;
import MVC.controller.MainController;
import MVC.controller.MakeGroupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno makeGroup a nastavujeme mu potrebne parametre*/
public class WindMakeGroup {
    public WindMakeGroup(ResourceBundle multiLang, Main main, MainController mainController) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/makeGroup.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 400, 80));
        primaryStage.setTitle(multiLang.getString("makeGroup"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        main.setSecondaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        MakeGroupController makeGroupController = loader.getController();
        makeGroupController.setMain(main);
        makeGroupController.setMainController(mainController);
    }
}
