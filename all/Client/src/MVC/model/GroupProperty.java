package MVC.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class GroupProperty {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty idUser;
    private final ListProperty<EventProperty> events;

    public GroupProperty(int id, String name, int idUser, ObservableList<EventProperty> events) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.events = new SimpleListProperty<EventProperty>(events);
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

    public ObservableList<EventProperty> getEvents() {
        return events.get();
    }

    public ListProperty<EventProperty> eventsProperty() {
        return events;
    }

    public void setEvents(ObservableList<EventProperty> events) {
        this.events.set(events);
    }
}