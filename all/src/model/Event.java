package model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Event implements Serializable {
    private final String name;
    private final String comment;
    private final Date date;
    private final String place;

    public Event(String name, String comment, Date date, String place) {
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

    public Date getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }


}
