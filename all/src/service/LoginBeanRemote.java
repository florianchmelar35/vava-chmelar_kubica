package service;

import javax.ejb.Remote;

@Remote
public interface LoginBeanRemote {

    boolean checkUser();
}
