package service;

import model.Group;
import model.User;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface, pomocou ktoreho instancie sa client prihlasi k stateless beane
 */

@Remote
public interface LoginBeanRemote {

    int checkUser(User user);
    List<Group> getData(int idUser);
    boolean signUpUser(User user);
    List<Group> refresh(User user, List<Group> list, List<Integer> deleted);
    boolean logout(User user, List<Group> list, List<Integer> deleted);

    Group addGroup(int idUser, int idGroup);
    boolean leaveGroup(int idUser, int idGroup);

}
