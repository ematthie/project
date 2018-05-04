package timetable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetable.DAO.*;
import timetable.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model implements Observable {

    private ArrayList<Lecture> lectures = new ArrayList<>();
    private ArrayList<Period> periods = new ArrayList<>();
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<Location> locations = FXCollections.observableArrayList();
    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private DataAccesProvider dataAccesProvider;

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    private void setLectures(ArrayList<Lecture> lectures) {
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

    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

    public ArrayList<Period> getPeriods() {
        return periods;
    }

    private void setPeriods(ArrayList<Period> periods) {
        this.periods = periods;
        fireInvalidationEvent();
    }

    public void updatePeriods() {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            PeriodDAO dao = dac.getPeriodDAO();
            setPeriods(dao.selectElements());
        } catch (Exception e) {
            System.err.println("mohow zeg");
        }
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    private void setStudents(ArrayList<Student> students) {
        this.students = FXCollections.observableArrayList(students);
        fireInvalidationEvent();
    }

    public ObservableList<Location> getLocations() {
        return locations;
    }

    private void setLocations(ArrayList<Location> locations) {
        this.locations = FXCollections.observableArrayList(locations);
        fireInvalidationEvent();
    }

    public ObservableList<Teacher> getTeachers() {
        return teachers;
    }

    private void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = FXCollections.observableArrayList(teachers);
        fireInvalidationEvent();
    }

    public void setUrl(String url) {
        dataAccesProvider = new JDBCDataAccesProvider(url);
        updatePeriods();
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            StudentDAO dao = dac.getStudentDAO();
            setStudents(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LocationDAO dao = dac.getLocationDAO();
            setLocations(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            TeacherDAO dao = dac.getTeacherDAO();
            setTeachers(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        fireInvalidationEvent();
    }

    public void updateModelByStudents(int id) {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LectureDAO dao = dac.getLectureDAO();
            setLectures(dao.selectElementsByRichting(id));
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
    }

    public void updateModelByLocation(int id) {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LectureDAO dao = dac.getLectureDAO();
            setLectures(dao.selectElementsByLocatie(id));
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
    }

    public void updateModelByTeacher(int id) {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LectureDAO dao = dac.getLectureDAO();
            setLectures(dao.selectElementsByTeacher(id));
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
    }

    public DataAccesProvider getDataAccesProvider() {
        return dataAccesProvider;
    }

    public void updateTeachers() {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            TeacherDAO dao = dac.getTeacherDAO();
            setTeachers(dao.selectElements());
        } catch (Exception e) {
            System.err.println("mohow zeg");
        }
    }

    public void updateStudents() {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            StudentDAO dao = dac.getStudentDAO();
            setStudents(dao.selectElements());
        } catch (Exception e) {
            System.err.println("mohow zeg");
        }
    }

    public void updateLocation() {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LocationDAO dao = dac.getLocationDAO();
            setLocations(dao.selectElements());
        } catch (Exception e) {
            System.err.println("mohow zeg");
        }
    }
}
