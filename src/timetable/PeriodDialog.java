package timetable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.PeriodDAO;
import timetable.DAO.TeacherDAO;

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
        Scene scene = new Scene(new VBox(uur, min, periodeToevoeger));
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
            if (minuut < 0 || minuut > 60) {
                min.setStyle("-fx-text-fill: red;");
            } else {
                min.setStyle("-fx-text-fill: black;");
                try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
                    PeriodDAO dao = dac.getPeriodDAO();
                    dao.addElement(hour, minuut);
                } catch (Exception e) {
                    System.out.println("haha kijk nu zeg");
                } finally {
                    model.updatePeriods();
                }
            }
        }
    }

}
