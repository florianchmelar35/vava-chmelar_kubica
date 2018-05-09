package MVC;

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

    public static boolean logout(User user, List<Group> list, List<Integer> deleted) throws Exception {
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.logout(user, list, deleted));
    }

    public static List<Group> refresh(User user, List<Group> list, List<Integer> deleted) throws Exception {
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.refresh(user, list, deleted));
    }

    public static Group addGroup (int idUser, int idGroup) throws Exception {
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.addGroup(idUser, idGroup));
    }

    public static boolean leaveGroup (int idUser, int idGroup) throws Exception {
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();

        return(statelessLoginBean.leaveGroup(idUser, idGroup));
    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {

        final Context context = new InitialContext();
        return (LoginBeanRemote) context.lookup("ejb:/project_OrganizeIT_war_exploded//LoginBean!service.LoginBeanRemote");
    }
}
