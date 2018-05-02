package timetable.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import timetable.Model;

import java.io.File;

public class MenuBarController {

    private Model model;

    public MenuBarController(Model model) {
        this.model = model;
    }

    public MenuBar populate(Stage stage) {
        final Menu menu = new Menu("File");
        MenuItem menuItem1 = new MenuItem("open");
        MenuItem menuItem2 = new MenuItem("new");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        menuItem1.setOnAction(event -> setURL(fileChooser.showOpenDialog(stage)));
        menu.getItems().addAll(menuItem1, menuItem2);
        return new MenuBar(menu);
    }

    private void setURL(File file) {
        try {
            model.setUrl(file.getAbsolutePath());
        } catch (Exception ex) {
            System.err.println("gelieve een bestand te kiezen");
        }
    }

}
