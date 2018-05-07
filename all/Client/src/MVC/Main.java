package MVC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/login.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 505, 255));
        primaryStage.show();

        //controller ma v sebe ulozenu tuto instanciu MainApp classy, aby s nou mohol pracovat
        MVC.controller.LoginController loginController = loader.getController();
        loginController.setMain(this);
    }



    public static void main(String[] args) {
        launch(args);

//        Dog dog = new Dog(1, "Sandy");
//
//        User user = new User(1, "gaga", "0000");
//
//        Gson gson = new Gson();
//        String temp = gson.toJson(dog);
//
//        System.out.println(dog);
//
//        Gson gson2 = new Gson();
//        Dog user2 = gson.fromJson(temp, Dog.class);
//
//        System.out.println(user2.getName());
//
//        try{
//            invokeLogin(temp);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }



    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
