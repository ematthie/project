package timetable.DAO;

import timetable.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCStudentDAO implements StudentDAO {

    private Connection connection;

    public JDBCStudentDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Student> selectElements() {
        ArrayList<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                students.add(new Student(id, name));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void addElement(String name) throws SQLException {
        ArrayList<Student> students = selectElements();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                throw new SQLException("Je hebt deze teacher al toegevoegd");
            }
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO students(name) VALUES (?);")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
