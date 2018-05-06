package client.controller.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;


public class WindMain extends Stage {
    public WindMain(ResourceBundle multiLang) throws Exception{
        super();
        Stage primaryStage=new Stage();
        Parent okno = FXMLLoader.load(getClass().getClassLoader().getResource("client/view/main.fxml"), multiLang);
        primaryStage.setTitle(multiLang.getString("mainWindow"));
        primaryStage.setScene(new Scene(okno, 600, 400));
        primaryStage.show();
    }
}
