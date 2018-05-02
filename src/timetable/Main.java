package timetable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import timetable.DAO.*;
import timetable.controllers.GridPaneController;
import timetable.controllers.AccordionController;

public class Main extends Application {

    private static Model model = new Model();
    private DataAccesProvider dataAccesProvider = new JDBCDataAccesProvider(model);

    @Override
    public void start(Stage stage) {
        final Menu menu1 = new Menu("File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        menu1.setOnAction(event -> fileChooser.showOpenDialog(stage));
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        GridPaneController gridPaneController = new GridPaneController(model);
        AccordionController accordionController = new AccordionController(model, dataAccesProvider);
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: whitesmoke;");
        borderPane.setTop(menuBar);
        borderPane.setLeft(accordionController.populate());
        GridPane gridPane = gridPaneController.populate();
        borderPane.setCenter(gridPane);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("rooster");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void getData() {
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            PeriodDAO dao = dac.getPeriodDAO();
            model.setPeriods(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            StudentDAO dao = dac.getStudentDAO();
            model.setStudents(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            LocationDAO dao = dac.getLocationDAO();
            model.setLocations(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
        try (DataAccesContext dac = dataAccesProvider.getDataAccessContext()) {
            TeacherDAO dao = dac.getTeacherDAO();
            model.setTeachers(dao.selectElements());
        } catch (Exception e) {
            System.err.println("Oopsie, didn't mean to!");
        }
    }

}
