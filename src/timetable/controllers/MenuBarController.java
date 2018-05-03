package timetable.controllers;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import timetable.DAO.LectureDAO;
import timetable.DAO.PeriodDAO;
import timetable.DataAccesContext;
import timetable.DataBaseCreator;
import timetable.Model;
import timetable.PeriodDialog;
import timetable.entity.Period;

import java.io.File;
import java.util.ArrayList;

public class MenuBarController {

    private Model model;

    public MenuBarController(Model model) {
        this.model = model;
    }

    public MenuBar populate(Stage stage) {
        final Menu menu = new Menu("File");
        MenuItem menuItem1 = new MenuItem("open");
        MenuItem menuItem2 = new MenuItem("new");
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Open Resource File");
        fileChooser1.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db"));
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Open Resource File");
        fileChooser2.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db"));
        menuItem1.setOnAction(event -> setURL(fileChooser1.showOpenDialog(stage)));
        menuItem2.setOnAction(event -> newDatabase(fileChooser2.showSaveDialog(stage)));
        menu.getItems().addAll(menuItem1, menuItem2);
        return new MenuBar(menu);
    }

    private void setURL(File file) {
        try {
            model.setUrl(file.getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("ongeldige databank");
        }
    }

    private void newDatabase(File file) {
        new DataBaseCreator(file.getAbsolutePath());
        model.setUrl(file.getAbsolutePath());
        new PeriodDialog(model);
    }

}
