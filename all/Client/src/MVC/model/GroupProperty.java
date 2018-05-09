package MVC.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
/**trieda ktora nam pomaha mapovat Groupy do tabulky*/
public class GroupProperty {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty idUser;

    public GroupProperty(int id, String name, int idUser) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.idUser = new SimpleIntegerProperty(idUser);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getIdUser() {
        return idUser.get();
    }

    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }

    @Override
    public String toString() {
        return name.get();
    }
}