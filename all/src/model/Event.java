package model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Event implements Serializable {
    private int id;
    private String name;
    private String comment;
    private Timestamp date;
    private String place;

    public Event(String name, String comment, Timestamp date, String place) {
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }


}
