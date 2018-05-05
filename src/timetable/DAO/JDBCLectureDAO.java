package timetable.DAO;

import timetable.entity.*;

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

    private ArrayList<Lecture> selectElements() {
        ArrayList<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture;")){
            ResultSet rs = stmt.executeQuery();
            lectures.addAll(makeLecture(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lectures;
    }

    public ArrayList<Lecture> selectElementsByRichting(int value) {
        ArrayList<Lecture> lectures = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE students_id = ?;")){
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
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE location_id = ?;")){
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
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lecture WHERE teacher_id = ?;")){
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            lectures.addAll(makeLecture(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lectures;
    }

    public void addElement(String name, int studentID, int teacherID, int locationID, int day, int periodID, Integer duration) throws SQLException {
        System.out.println(day);
        ArrayList<Lecture> lectures = selectElements();
        for (Lecture lecture : lectures) {
            if (lecture.getCourse().equals(name)
                    && lecture.getStudentsID() == studentID
                    && lecture.getTeacherID() == teacherID
                    && lecture.getLocationID() == locationID
                    && lecture.getDay() == day
                    && lecture.getStart() == periodID
                    && lecture.getDuration() == duration) {
                throw new SQLException("Je hebt deze lecture al toegevoegd");
            }
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO lecture VALUES (?, ?, ?, ?, ?, ?, ?);")) {
            stmt.setInt(1, studentID);
            stmt.setInt(2, teacherID);
            stmt.setInt(3, locationID);
            stmt.setString(4, name);
            stmt.setInt(5, day);
            stmt.setInt(6, periodID);
            stmt.setInt(7, duration);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (Lecture lecture : selectElements()) {
            System.out.println("" + lecture.getDuration() + lecture.getStart() + lecture.getDay());
        }
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
