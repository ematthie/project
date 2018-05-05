package timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {

    public DataBaseCreator(String url) {
        try (Connection conn = new JDBCDataAccesProvider(url).getConnection()) {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("drop table period;");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("drop table lecture;");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("drop table teacher;");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("drop table students;");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("drop table location;");
            stmt.executeUpdate();
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
            System.err.println("negeer deze errors please");
        }
    }

}
