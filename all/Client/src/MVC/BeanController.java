package MVC;

import model.Event;
import model.Group;
import model.User;
import service.LoginBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BeanController {


    public static void main(String[] args) {

        User user = new User(1, "gaga", "0000");
        User user2 = new User(2, "koko", "4444");

        Event ev = new Event("TOTO", "Hej", new Date(2018, 4, 3), "FIIT");
        Event ev2 = new Event("KOKO", "NIE", new Date(2018, 4, 3), "FRI");

        List<Event> list = new ArrayList<>();
        list.add(ev);
        list.add(ev2);

        Group group = new Group("FIIT", 1, list);


        try{
            invokeLogin(group);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    static void invokeLogin(Group group) throws Exception{
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        Group income = statelessLoginBean.checkUser(group);

        System.out.println(income.getIdUser() + " " + income.getName());


    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {

        final Context context = new InitialContext();
        return (LoginBeanRemote) context.lookup("ejb:/project_OrganizeIT_ejb_exploded//LoginBean!service.LoginBeanRemote");
    }
}
