package client.controller.window;

import client.Main;
import client.controller.ListGroupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class WindListGroup {
    public WindListGroup(ResourceBundle multiLang, Main main) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/view/listGroup.fxml"), multiLang);
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
    }
}
