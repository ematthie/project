package timetable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timetable.controllers.GridPaneController;
import timetable.controllers.AccordionController;
import timetable.controllers.MenuBarController;

public class Main extends Application {

    private Model model = new Model();

    @Override
    public void start(Stage stage) {
        MenuBarController menuBarController = new MenuBarController(model);
        GridPaneController gridPaneController = new GridPaneController(model);
        AccordionController accordionController = new AccordionController(model);
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: whitesmoke;");
        borderPane.setTop(menuBarController.populate(stage));
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
}
