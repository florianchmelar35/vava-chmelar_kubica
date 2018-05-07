package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private String name;
    private int idUser;
    private List<Event> events;

    public Group(String name, int idUser, List<Event> events) {
        this.name = name;
        this.idUser = idUser;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
