package service;

import model.Group;
import model.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface LoginBeanRemote {

    int checkUser(User user);
    List<Group> getData(int idUser);
    boolean signUpUser(User user);
    List<Group> refresh(User user, List<Group> list);
    boolean logout(User user, List<Group> list);

}
