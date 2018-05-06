package timetable.dialogs;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.LectureDAO;
import timetable.DataAccesContext;
import timetable.Model;
import timetable.entity.*;

public class editLectureDialog {

    private Model model;
    private ListView<Lecture> lectureListView;
    private TextField lectureTextField;
    private ComboBox<Student> studentComboBox;
    private ComboBox<Teacher> teacherComboBox;
    private ComboBox<Location> locationComboBox;
    private ComboBox<String> dayComboBox;
    private ComboBox<Period> periodComboBox;
    private Spinner<Integer> durationSpinner;

    public editLectureDialog(Model model) {
        this.model = model;
        Stage stage = new Stage();
        lectureListView = new ListView<>(FXCollections.observableArrayList(model.getLectures()));
        lectureListView.getSelectionModel().selectedItemProperty().addListener(
                ob -> vulVelden()
        );
        Label lectureLabel = new Label("lecture");
        lectureTextField = new TextField();
        HBox lectureHBox = new HBox(lectureLabel, lectureTextField);
        Label studentsLabel = new Label("students");
        studentComboBox = new ComboBox<>(model.getStudents());
        HBox studentsHBox = new HBox(studentsLabel, studentComboBox);
        Label teacherLabel = new Label("teacher");
        teacherComboBox = new ComboBox<>(model.getTeachers());
        HBox teacherHBox = new HBox(teacherLabel, teacherComboBox);
        Label locationLabel = new Label("location");
        locationComboBox = new ComboBox<>(model.getLocations());
        HBox locationHBox = new HBox(locationLabel, locationComboBox);
        Label dayLabel = new Label("day");
        dayComboBox = new ComboBox<>();
        dayComboBox.getItems().addAll("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag");
        HBox dayHBox = new HBox(dayLabel, dayComboBox);
        Label periodLabel = new Label("period");
        periodComboBox = new ComboBox<>(FXCollections.observableArrayList(model.getPeriods()));
        HBox periodHBox = new HBox(periodLabel, periodComboBox);
        Label durationLabel = new Label("duration");
        durationSpinner = new Spinner<>(1, model.getPeriods().size(), 1);
        HBox durationHBox = new HBox(durationLabel, durationSpinner);
        Button edit = new Button("edit");
        edit.setOnAction(event -> editLecture(stage));
        Button delete = new Button("delete");
        delete.setOnAction(event -> deleteLecture(stage));
        Scene scene = new Scene(new VBox(lectureListView, lectureHBox, studentsHBox, teacherHBox, locationHBox, dayHBox, periodHBox, durationHBox, edit, delete));
        stage.setScene(scene);
        stage.setTitle("voeg periode(s) toe");
        stage.show();
    }

    private void deleteLecture(Stage stage) {
        Lecture lecture = lectureListView.getSelectionModel().getSelectedItem();
        try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
            LectureDAO dao = dac.getLectureDAO();
            dao.removeElement(lecture);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Gelieve nieuwe naam te speciefieren");
            alert.show();
        } finally {
            stage.close();
        }
    }

    private void vulVelden() {
        Lecture lecture = lectureListView.getSelectionModel().getSelectedItem();
        lectureTextField.setText(lecture.getCourse());
        studentComboBox.getSelectionModel().select(lecture.getStudentsID() - 1);
        teacherComboBox.getSelectionModel().select(lecture.getTeacherID() - 1);
        locationComboBox.getSelectionModel().select(lecture.getLocationID() - 1);
        dayComboBox.getSelectionModel().select(lecture.getDay() - 1);
        periodComboBox.getSelectionModel().select(lecture.getStart() - 1);
        durationSpinner.getValueFactory().setValue(lecture.getDuration());
    }

    private void editLecture(Stage stage) {
        Lecture lecture = lectureListView.getSelectionModel().getSelectedItem();
        try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
            LectureDAO dao = dac.getLectureDAO();
            dao.removeElement(lecture);
            dao.addElement(lectureTextField.getText(),
                    studentComboBox.getSelectionModel().getSelectedItem().getId(),
                    teacherComboBox.getSelectionModel().getSelectedItem().getId(),
                    locationComboBox.getSelectionModel().getSelectedItem().getId(),
                    dayComboBox.getSelectionModel().getSelectedIndex() + 1,
                    periodComboBox.getSelectionModel().getSelectedItem().getId(),
                    durationSpinner.getValue()
            );
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Gelieve nieuwe naam te speciefieren");
            alert.show();
        } finally {
            stage.close();
        }
    }

}
