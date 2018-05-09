package MVC.controller.window;

import MVC.Main;
import MVC.controller.ListGroupController;
import MVC.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno listGroup a nastavujeme mu potrebne parametre*/
public class WindListGroup {
    public WindListGroup(ResourceBundle multiLang, Main main, MainController mainController) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/listGroup.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle(multiLang.getString("listGroup"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        //main.setPrimaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        ListGroupController listGroupController = loader.getController();
        listGroupController.setMain(main);
        listGroupController.setMainController(mainController);
        listGroupController.initialization();
    }
}
