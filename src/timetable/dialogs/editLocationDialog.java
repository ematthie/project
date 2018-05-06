package timetable.dialogs;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetable.DAO.LocationDAO;
import timetable.DataAccesContext;
import timetable.Model;
import timetable.entity.Location;

public class editLocationDialog {

    private Model model;
    private ComboBox<Location> locationComboBox;
    private TextField nieuw;

    public editLocationDialog(Model model) {
        this.model = model;
        Label oudLabel = new Label("oud");
        locationComboBox = new ComboBox<>(model.getLocations());
        HBox oudHBox = new HBox(oudLabel, locationComboBox);
        Label nieuwLabel = new Label("nieuw");
        nieuw = new TextField();
        HBox nieuwHBox = new HBox(nieuwLabel, nieuw);
        Stage stage = new Stage();
        Button edit = new Button("edit");
        edit.setOnAction(event -> editLocation(stage));
        Scene scene = new Scene(new VBox(oudHBox, nieuwHBox, edit));
        stage.setScene(scene);
        stage.setTitle("voeg periode(s) toe");
        stage.show();
    }

    private void editLocation(Stage stage) {
        try (DataAccesContext dac = model.getDataAccesProvider().getDataAccessContext()) {
            LocationDAO dao = dac.getLocationDAO();
            dao.updateElement(locationComboBox.getSelectionModel().getSelectedItem().getId(), nieuw.getText());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Gelieve nieuwe naam te speciefieren");
            alert.show();
        } finally {
            model.updateLocation();
            stage.close();
        }
    }

}
