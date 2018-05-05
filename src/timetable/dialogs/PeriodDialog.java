package timetable.dialogs;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.PeriodDAO;
import timetable.DAO.TeacherDAO;
import timetable.DataAccesContext;
import timetable.Model;

import java.sql.SQLException;
import java.util.Optional;

public class PeriodDialog extends Dialog {

    private TextField uur = new TextField("voeg uur toe");
    private TextField min = new TextField("voeg minuut toe");
    private Model model;

    public PeriodDialog(Model model) {
        this.model = model;
        Stage stage = new Stage();
        Button periodeToevoeger = new Button("voeg periode toe");
        periodeToevoeger.setOnAction(event -> voegPeriodeToe());
        Button klaar = new Button("klaar");
        klaar.setOnAction(event -> klaar(stage));
        Scene scene = new Scene(new VBox(new HBox(uur, min), periodeToevoeger, klaar));
        stage.setScene(scene);
        stage.setTitle("voeg periode(s) toe");
        stage.show();
    }

    private void voegPeriodeToe() {
        int hour;
        try {
            hour = Integer.parseInt(uur.getText(), 10);
        } catch (Exception e) {
            uur.setStyle("-fx-text-fill: red;");
            hour = -1;
        }
        if (hour < 0 || hour > 23) {
            uur.setStyle("-fx-text-fill: red;");
        } else {
            uur.setStyle("-fx-text-fill: black;");
            int minuut;
            try {
                minuut = Integer.parseInt(min.getText(), 10);
            } catch (Exception e) {
                min.setStyle("-fx-text-fill: red;");
                minuut = -1;
            }
            if (minuut < 0 || minuut > 59) {
                min.setStyle("-fx-text-fill: red;");
            } else {
                min.setStyle("-fx-text-fill: black;");
                try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
                    PeriodDAO dao = dac.getPeriodDAO();
                    dao.addElement(hour, minuut);
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(e.getMessage());
                    alert.show();
                } catch (Exception e){
                    // TODO
                }
            }
        }
    }

    private void klaar(Stage stage) {
        model.updatePeriods();
        stage.close();
    }

}
