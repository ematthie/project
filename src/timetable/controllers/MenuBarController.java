package timetable.controllers;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import timetable.*;
import timetable.DAO.LocationDAO;
import timetable.DAO.StudentDAO;
import timetable.DAO.TeacherDAO;
import timetable.dialogs.LectureDialog;
import timetable.dialogs.PeriodDialog;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

public class MenuBarController {

    private Model model;

    public MenuBarController(Model model) {
        this.model = model;
    }

    public MenuBar populate(Stage stage) {
        Menu file = new Menu("File");
        MenuItem open = new MenuItem("open");
        MenuItem aNew = new MenuItem("new");
        FileChooser openResourceFile = new FileChooser();
        openResourceFile.setTitle("Open Resource File");
        openResourceFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db"));
        FileChooser saveRecourseFile = new FileChooser();
        saveRecourseFile.setTitle("Open Resource File");
        saveRecourseFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db"));
        open.setOnAction(event -> setURL(openResourceFile.showOpenDialog(stage)));
        aNew.setOnAction(event -> newDatabase(saveRecourseFile.showSaveDialog(stage)));
        file.getItems().addAll(open, aNew);
        Menu add = new Menu("Add");
        MenuItem student = new MenuItem("Student");
        student.setOnAction(event -> addStudent());
        MenuItem teacher = new MenuItem("Teacher");
        teacher.setOnAction(event -> addTeacher());
        MenuItem location = new MenuItem("Location");
        location.setOnAction(event -> addLocation());
        MenuItem lecture = new MenuItem("Lecture");
        lecture.setOnAction(event -> addLecture());
        Menu edit = new Menu("edit");
        MenuItem studentEdit = new MenuItem("student");
        studentEdit.setOnAction(event -> editStudent());
        add.getItems().addAll(student, teacher, location, lecture);
        return new MenuBar(file, add);
    }

    private void editStudent() {
//
    }

    private void addLecture() {
        new LectureDialog(model);
    }

    private void setURL(File file) {
        try {
            model.setUrl(file.getAbsolutePath());
        } catch (Throwable ex) {
            System.out.println("ongeldige databank");
        }
    }

    private void newDatabase(File file) {
        if (file != null) {
            new DataBaseCreator(file.getAbsolutePath());
            model.setUrl(file.getAbsolutePath());
            new PeriodDialog(model);
        }
    }

    private void addTeacher() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add teacher");
            dialog.setHeaderText("Enter the name of the teacher");
            dialog.setContentText("Name:");
            Optional<String> naam = dialog.showAndWait();
            try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
                TeacherDAO dao = dac.getTeacherDAO();
                if (naam.isPresent()) {
                    dao.addElement(naam.get());
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            } catch (Exception e){
                // TODO
            } finally {
                model.updateTeachers();
            }
        } catch (Exception e) {
            //TODO
        }
    }

    private void addStudent() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add student group");
            dialog.setHeaderText("Enter the name of the group");
            dialog.setContentText("Name:");
            Optional<String> naam = dialog.showAndWait();
            try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
                StudentDAO dao = dac.getStudentDAO();
                if (naam.isPresent()) {
                    dao.addElement(naam.get());
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            } catch (Exception e){
                // TODO
            } finally {
                model.updateStudents();
            }
        } catch (Exception e) {
            //TODO
        }
    }

    private void addLocation() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add a location");
            dialog.setHeaderText("Enter the name of the location");
            dialog.setContentText("Name:");
            Optional<String> naam = dialog.showAndWait();
            try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
                LocationDAO dao = dac.getLocationDAO();
                if (naam.isPresent()) {
                    dao.addElement(naam.get());
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.show();
            } catch (Exception e){
                // TODO
            }
            finally {
                model.updateLocation();
            }
        } catch (Exception e) {
            //TODO
        }
    }

}
