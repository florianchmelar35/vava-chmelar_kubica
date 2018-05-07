package service;

import com.google.gson.Gson;
import model.Group;

import javax.ejb.Remote;
import javax.ejb.Stateless;


@Stateless
@Remote (LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote{
//    Gson gson = new Gson();
    public LoginBean() {
    }

    public Group checkUser(Group group) {
//        return true;

        return group;

    }

    public boolean checkUser(String temp) {
        Gson gson = new Gson();

//        User user = gson.fromJson(temp, User.class);
//
//        if (user.getPassword().equals("0000"))
//            return true;

//        Dog user = gson.fromJson(temp, Dog.class);
//
//        if (user.getName().equals("Sandy"))
//            return true;
//
        return false;
    }

//    public boolean checkUser(User user) {
////        return true;
//
//        if (user.getPassword()){
//            return true;
//        }
//
//        return false;
//    }

    public boolean checkUser() {
        return true;
    }


}
