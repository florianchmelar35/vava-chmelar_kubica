package MVC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.LoginBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;

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
//
//
////        try{
////            invokeLogin();
////
////        }
////        catch (Exception e){
////            e.printStackTrace();
////        }
//
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    static void invokeLogin() throws Exception{
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        boolean bool = statelessLoginBean.checkUser();
        if(bool)
            System.out.println("HEJEEEE");

    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        Properties prop= new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        final Context context = new InitialContext(prop);
        return (LoginBeanRemote) context.lookup("ejb:/project_OrganizeIT_ear_exploded/ejb//TestService!service.TestService");
    }
}
