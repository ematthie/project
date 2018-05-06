package timetable.DAO;

import timetable.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LectureDAO {

    ArrayList<Lecture> selectElementsByRichting(int value);

    ArrayList<Lecture> selectElementsByLocatie(int value);

    ArrayList<Lecture> selectElementsByTeacher(int value);

    void addElement(String name, int studentID, int teacherID, int locationID, int day, int periodID, Integer duration) throws SQLException;

    void removeElement(Lecture lecture);
}
