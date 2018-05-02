package timetable.DAO;

import timetable.entity.Lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCLectureDAO implements LectureDAO {

    private Connection connection;

    public JDBCLectureDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Lecture> selectElementsByRichting(int value) {
        ArrayList<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE students_id = ?")){
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            lectures.addAll(makeLecture(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lectures;
    }

    public ArrayList<Lecture> selectElementsByLocatie(int value) {
        ArrayList<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE location_id = ?")){
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            lectures.addAll(makeLecture(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lectures;
    }

    public ArrayList<Lecture> selectElementsByTeacher(int value) {
        ArrayList<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE teacher_id = ?")){
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            lectures.addAll(makeLecture(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lectures;
    }

    private ArrayList<Lecture> makeLecture(ResultSet resultSet) throws SQLException {
        ArrayList<Lecture> lectures = new ArrayList<>();
        while (resultSet.next()) {
            int studentsID = resultSet.getInt(1);
            int teacherID = resultSet.getInt(2);
            int locationID = resultSet.getInt(3);
            String course = resultSet.getString(4);
            int day = resultSet.getInt(5);
            int start = resultSet.getInt(6);
            int duration = resultSet.getInt(7);
            lectures.add(new Lecture(studentsID, teacherID, locationID, course, day, start, duration));
        }
        return lectures;
    }
}
