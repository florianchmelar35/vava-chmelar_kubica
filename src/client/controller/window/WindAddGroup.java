package client.controller.window;

import client.Main;
import client.controller.AddGroupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class WindAddGroup {
    public WindAddGroup(ResourceBundle multiLang, Main main) throws Exception{
        super();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/view/addGroup.fxml"), multiLang);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 400, 80));
        primaryStage.setTitle(multiLang.getString("addGroup"));
        primaryStage.show();

        //prepnutie okien
        //main.getPrimaryStage().close();
        //main.setPrimaryStage(primaryStage);

        //controller potrebuje mat pristup k main classe
        AddGroupController addGroupController = loader.getController();
        addGroupController.setMain(main);
    }
}
