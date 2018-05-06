package timetable.dialogs;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.StudentDAO;
import timetable.DataAccesContext;
import timetable.Model;
import timetable.entity.Student;

public class editStudentDialog {

    private Model model;
    private ComboBox<Student> studentComboBox;
    private TextField nieuw;

    public editStudentDialog(Model model) {
        this.model = model;
        Label oudLabel = new Label("oud");
        studentComboBox = new ComboBox<>(model.getStudents());
        HBox oudHBox = new HBox(oudLabel, studentComboBox);
        Label nieuwLabel = new Label("nieuw");
        nieuw = new TextField();
        HBox nieuwHBox = new HBox(nieuwLabel, nieuw);
        Stage stage = new Stage();
        Button edit = new Button("edit");
        edit.setOnAction(event -> editStudent(stage));
        Scene scene = new Scene(new VBox(oudHBox, nieuwHBox, edit));
        stage.setScene(scene);
        stage.setTitle("voeg periode(s) toe");
        stage.show();
    }

    private void editStudent(Stage stage) {
        try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
            StudentDAO dao = dac.getStudentDAO();
            dao.updateElement(studentComboBox.getSelectionModel().getSelectedItem().getId(), nieuw.getText());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Gelieve nieuwe naam te speciefieren");
            alert.show();
        } finally {
            model.updateStudents();
            stage.close();
        }
    }

}
