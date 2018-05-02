package timetable.DAO;

import timetable.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCTeacherDAO implements TeacherDAO {

    private Connection connection;

    public JDBCTeacherDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Teacher> selectElements() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM teacher")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                teachers.add(new Teacher(id, name));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teachers;
    }
}
