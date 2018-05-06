package MVC.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Group {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty idUser;
    private final ListProperty<Event> events;

    public Group(int id, String name, int idUser, ObservableList<Event> events) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.events = new SimpleListProperty<Event>(events);
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

    public ObservableList<Event> getEvents() {
        return events.get();
    }

    public ListProperty<Event> eventsProperty() {
        return events;
    }

    public void setEvents(ObservableList<Event> events) {
        this.events.set(events);
    }
}
