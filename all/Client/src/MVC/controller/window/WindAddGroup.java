package MVC.controller.window;

import MVC.Main;
import MVC.controller.AddGroupController;
import MVC.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**trieda spusta nove okno addGroup a nastavujeme mu potrebne parametre*/
public class WindAddGroup {
    public WindAddGroup(ResourceBundle multiLang, Main main, MainController mainController) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MVC/view/addGroup.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 400, 80));
        primaryStage.setTitle(multiLang.getString("addGroup"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        main.setSecondaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        AddGroupController addGroupController = loader.getController();
        addGroupController.setMain(main);
        addGroupController.setMainController(mainController);
    }
}
