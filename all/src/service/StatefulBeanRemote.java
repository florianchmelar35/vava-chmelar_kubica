//package service;
//
//import model.Group;
//import model.User;
//
//import javax.ejb.Remote;
//import java.util.List;
//
//@Remote
//public interface StatefulBeanRemote {
//    int checkUser(User user);
//    List<Group> getData();
//    boolean signUpUser(User user);
//    List<Group> refresh(List<Group> list, List<Integer> deleted, List<Integer> created);
//    boolean logout(List<Group> list, List<Integer> deleted, List<Integer> created);
//
//    Group addGroup(int idGroup);
//    boolean leaveGroup(int idGroup);
//
//}
