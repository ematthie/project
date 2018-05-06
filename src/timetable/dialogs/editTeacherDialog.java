package timetable.dialogs;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.TeacherDAO;
import timetable.DataAccesContext;
import timetable.Model;
import timetable.entity.Teacher;

public class editTeacherDialog {

    private Model model;
    private ComboBox<Teacher> teacherComboBox;
    private TextField nieuw;

    public editTeacherDialog(Model model) {
        this.model = model;
        Label oudLabel = new Label("oud");
        teacherComboBox = new ComboBox<>(model.getTeachers());
        HBox oudHBox = new HBox(oudLabel, teacherComboBox);
        Label nieuwLabel = new Label("nieuw");
        nieuw = new TextField();
        HBox nieuwHBox = new HBox(nieuwLabel, nieuw);
        Stage stage = new Stage();
        Button edit = new Button("edit");
        edit.setOnAction(event -> editTeacher(stage));
        Scene scene = new Scene(new VBox(oudHBox, nieuwHBox, edit));
        stage.setScene(scene);
        stage.setTitle("voeg periode(s) toe");
        stage.show();
    }

    private void editTeacher(Stage stage) {
        try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
            TeacherDAO dao = dac.getTeacherDAO();
            dao.updateElement(teacherComboBox.getSelectionModel().getSelectedItem().getId(), nieuw.getText());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Gelieve nieuwe naam te speciefieren");
            alert.show();
        } finally {
            model.updateTeachers();
            stage.close();
        }
    }

}
