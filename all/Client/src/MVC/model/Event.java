package MVC.model;

import javafx.beans.property.*;

import java.sql.Timestamp;

public class Event {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty comment;
    private final ObjectProperty<Timestamp> date;
    private final StringProperty place;

    public Event(int id, String name, String comment, Timestamp date, String place) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.comment = new SimpleStringProperty(comment);
        this.date = new SimpleObjectProperty<Timestamp>(date);
        this.place = new SimpleStringProperty(place);
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

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public Timestamp getDate() {
        return date.get();
    }

    public ObjectProperty<Timestamp> dateProperty() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date.set(date);
    }

    public String getPlace() {
        return place.get();
    }

    public StringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }
}
