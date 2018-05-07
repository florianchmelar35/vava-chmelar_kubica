package MVC;

import model.Event;
import model.Group;
import model.User;
import service.LoginBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class BeanController {

    public static int checkUser(User user) throws Exception{
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return statelessLoginBean.checkUser(user);
    }

    public static List<Group> getData(int idUser) throws Exception{
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.getData(idUser));
    }

    public static boolean signUpUser(User user) throws Exception{
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.signUpUser(user));
    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {

        final Context context = new InitialContext();
        return (LoginBeanRemote) context.lookup("ejb:/project_OrganizeIT_war_exploded//LoginBean!service.LoginBeanRemote");
    }
}
