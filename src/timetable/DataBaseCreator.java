package timetable;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {

    public DataBaseCreator(String url) {
        try (Connection conn = new JDBCDataAccesProvider(url).getConnection()) {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("create table period (\"id\" integer primary key, \"hour\", \"minute\");");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("create table students (\"id\" integer primary key, \"name\");");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("create table teacher (\"id\" integer primary key, \"name\");");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("create table location (\"id\" integer primary key, \"name\");");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("create table lecture (\"students_id\", \"teacher_id\", \"location_id\", \"course\", \"day\", \"first_block\", \"duration\");");
            stmt.executeUpdate();
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Gelieve een andere databank te openen");
            alert.show();
        }
    }

}
