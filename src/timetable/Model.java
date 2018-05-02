package timetable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetable.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observable {

    private ArrayList<Lecture> lectures = new ArrayList<>();
    private ArrayList<Period> periods = new ArrayList<>();
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<Location> locations = FXCollections.observableArrayList();
    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private String url; // = "jdbc:sqlite::resource:timetable/lectures.db";

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
        fireInvalidationEvent();
    }

    private List<InvalidationListener> listenerList = new ArrayList<>();

    @Override
    public void addListener(InvalidationListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listenerList.remove(listener);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

    public ArrayList<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(ArrayList<Period> periods) {
        this.periods = periods;
        fireInvalidationEvent();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = FXCollections.observableArrayList(students);
        fireInvalidationEvent();
    }

    public ObservableList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = FXCollections.observableArrayList(locations);
        fireInvalidationEvent();
    }

    public ObservableList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = FXCollections.observableArrayList(teachers);
        fireInvalidationEvent();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        fireInvalidationEvent();
    }
}
