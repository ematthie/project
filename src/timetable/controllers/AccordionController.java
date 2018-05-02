package timetable.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import timetable.DAO.LectureDAO;
import timetable.DataAccesProvider;
import timetable.DataAccesContext;
import timetable.Model;
import timetable.entity.Location;
import timetable.entity.Student;
import timetable.entity.Teacher;

public class AccordionController implements InvalidationListener{

    private Model model;
    private ListView<Student> studentListView = new ListView<>();
    private ListView<Teacher> teacherListView = new ListView<>();
    private ListView<Location> locationListView = new ListView<>();

    public AccordionController(Model model) {
        this.model = model;
        model.addListener(this);
    }

    public Accordion populate() {
        ObservableList<Student> students = FXCollections.observableArrayList(model.getStudents());
        studentListView.setItems(students);
        studentListView.getSelectionModel().selectedItemProperty().addListener(
                ob -> model.updateModelByStudents(studentListView.getSelectionModel().getSelectedItem().getId())
        );
        TitledPane t1 = new TitledPane("By Students", studentListView);
        t1.setMaxHeight(Double.POSITIVE_INFINITY);
        ObservableList<Location> locations = FXCollections.observableArrayList(model.getLocations());
        locationListView.setItems(locations);
        locationListView.getSelectionModel().selectedItemProperty().addListener(
                ob -> model.updateModelByLocation(locationListView.getSelectionModel().getSelectedItem().getId())
        );
        TitledPane t2 = new TitledPane("By Location", locationListView);
        t2.setMaxHeight(Double.POSITIVE_INFINITY);
        ObservableList<Teacher> teachers = FXCollections.observableArrayList(model.getTeachers());
        teacherListView.setItems(teachers);
        teacherListView.getSelectionModel().selectedItemProperty().addListener(
                ob -> model.updateModelByTeacher(teacherListView.getSelectionModel().getSelectedItem().getId())
        );
        TitledPane t3 = new TitledPane("By Professor", teacherListView);
        t3.setMaxHeight(Double.POSITIVE_INFINITY);
        Accordion accordion = new Accordion();
        accordion.setPrefHeight(5000);
        accordion.getPanes().addAll(t1, t2, t3);
        accordion.setPadding(new Insets(0, 0, 0, 0));
        accordion.setPadding(new Insets(10, 5, 10, 10));
        return accordion;
    }

    @Override
    public void invalidated(Observable observable) {
        studentListView.setItems(model.getStudents());
        teacherListView.setItems(model.getTeachers());
        locationListView.setItems(model.getLocations());
    }
}
