package service;

import model.Group;

import javax.ejb.Remote;

@Remote
public interface LoginBeanRemote {

    Group checkUser(Group group);
    boolean checkUser(String temp);
    boolean checkUser();

}
