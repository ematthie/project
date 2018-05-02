package timetable.controllers;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import timetable.Model;
import timetable.entity.Lecture;
import timetable.entity.Period;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class GridPaneController implements InvalidationListener {

    private GridPane gridPane = new GridPane();
    private ArrayList<Period> periods;
    private int[][] overlappingen;
    private HashMap<Integer, Label> integerLabelHashMap = new HashMap<>();
    private Model model;

    public GridPaneController(Model model) {
        this.periods = model.getPeriods();
        this.model = model;
        model.addListener(this::invalidated);
        this.overlappingen = new int[5][periods.size()];
        plaats();
    }

    public GridPane populate() {
        gridPane.setPadding(new Insets(10, 10, 10, 5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(5);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(19);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(19);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(19);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(19);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(19);
        basis();
        gridPane.getColumnConstraints().addAll(column0, column1, column2, column3, column4, column5);
        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(5);
        gridPane.getRowConstraints().add(rowConstraints0);
        for (int i = 0; i < periods.size(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(19);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        return gridPane;
    }

    private void basis() {
        Label uren = new Label("Uren");
        uren.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        uren.setAlignment(Pos.CENTER);
        uren.setStyle("-fx-background-color: C0C0C0;");
        Label maandag = new Label("Maandag");
        maandag.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        maandag.setAlignment(Pos.CENTER);
        maandag.setStyle("-fx-background-color: C0C0C0;");
        Label dinsdag = new Label("Dinsdag");
        dinsdag.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        dinsdag.setAlignment(Pos.CENTER);
        dinsdag.setStyle("-fx-background-color: C0C0C0;");
        Label woensdag = new Label("Woensdag");
        woensdag.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        woensdag.setAlignment(Pos.CENTER);
        woensdag.setStyle("-fx-background-color: C0C0C0;");
        Label donderdag = new Label("Donderdag");
        donderdag.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        donderdag.setAlignment(Pos.CENTER);
        donderdag.setStyle("-fx-background-color: C0C0C0;");
        Label vrijdag = new Label("Vrijdag");
        vrijdag.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        vrijdag.setAlignment(Pos.CENTER);
        vrijdag.setStyle("-fx-background-color: C0C0C0;");
        gridPane.add(uren, 0, 0);
        gridPane.add(maandag, 1, 0);
        gridPane.add(dinsdag, 2, 0);
        gridPane.add(woensdag, 3, 0);
        gridPane.add(donderdag, 4, 0);
        gridPane.add(vrijdag, 5, 0);

        int i = 1;
        for (Period period : periods) {
            Label label = new Label(period.toString());
            label.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-background-color: C0C0C0;");
            gridPane.add(label, 0, i);
            i++;
        }

        overlappingen = new int[5][periods.size()];
    }

    public void plaats() {
        ArrayList<Lecture> lectures = model.getLectures();
        gridPane.getChildren().clear();
        basis();
        for (Lecture lecture : lectures) {
            int duration = lecture.getDuration();
            for (int i=0; i < duration; i++) {
                int kol = lecture.getDay();
                int rij = lecture.getStart() + i;
                Label label = new Label(lecture.getCourse());
                label.setPrefSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                label.setAlignment(Pos.TOP_LEFT);
                label.setTextFill(Paint.valueOf("white"));
                if (overlappingen[kol-1][rij-1] < 1) {
                    label.setStyle("-fx-background-color: green;");
                    label.setPadding(new Insets(5, 5, 5, 5));
                    gridPane.add(label, kol, rij);
                    integerLabelHashMap.put(5*rij + kol, label);
                    overlappingen[kol-1][rij-1]++;
                }
                else {
                    Label label1 = integerLabelHashMap.get(5*rij + kol);
                    label1.setText(label1.getText() + '\n' + lecture.getCourse());
                    label1.setStyle("-fx-background-color: red;");
                    integerLabelHashMap.replace(5*rij + kol, label1);
                }
            }
        }
    }

    @Override
    public void invalidated(javafx.beans.Observable observable) {
        plaats();
    }
}
