//package service;
//
//import model.User;
//
//import javax.ejb.Stateful;
//
//@Stateful
//public class TestFull implements LoginBeanRemote{
//
//    public TestFull() {
//    }
//
//    @Override
//    public boolean checkUser(Object o) {
//
//        if (o instanceof User){
//            User user = (User) o;
//            if (user.getPassword().equals("0000"))
//                return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean checkUser() {
//        return false;
//    }
//}
