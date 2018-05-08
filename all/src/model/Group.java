package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private int id;
    private String name;
    private int idUser;
    private boolean personal;
    private List<Event> events;

    public Group(String name, int idUser, List<Event> events) {
        this.name = name;
        this.idUser = idUser;
        this.events = events;
    }

    public Group(int id, String name, int idUser, boolean personal) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
        this.personal = personal;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }
}
