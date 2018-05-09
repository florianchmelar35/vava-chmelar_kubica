package MVC.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;
/**trieda ktora nam pomaha mapovat Eventy do tabulky*/
public class EventProperty {
    private IntegerProperty id;
    private final StringProperty name;
    private final StringProperty comment;
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<LocalTime> time;
    private final StringProperty place;

    public EventProperty(String name, String comment, LocalDate date, LocalTime time, String place) {
        this.name = new SimpleStringProperty(name);
        this.comment = new SimpleStringProperty(comment);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.time = new SimpleObjectProperty<LocalTime>(time);
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

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time.set(time);
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
