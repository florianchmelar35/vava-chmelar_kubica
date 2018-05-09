package MVC.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class UserPoperty {
    private IntegerProperty id;
    private StringProperty login;
    private StringProperty password;

    public UserPoperty(StringProperty login, StringProperty password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
