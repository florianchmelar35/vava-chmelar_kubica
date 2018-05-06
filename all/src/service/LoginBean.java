package service;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote (LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote{
    public LoginBean() {
    }

    @Override
    public boolean checkUser() {
        if (1==1)
            return true;
        return false;
    }
}
