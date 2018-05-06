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

    public void addElement(String name) throws SQLException {
        ArrayList<Teacher> teachers = selectElements();
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                throw new SQLException("Je hebt deze teacher al toegevoegd");
            }
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO teacher(name) VALUES (?);")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateElement(int id, String text) {
        try (PreparedStatement stmnt = connection.prepareStatement("UPDATE teacher SET name = ? WHERE id = ?;")) {
            stmnt.setString(1, text);
            stmnt.setInt(2, id);
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            // TODO
        }
    }
}
