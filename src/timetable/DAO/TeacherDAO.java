package timetable.DAO;

import timetable.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherDAO {

    public ArrayList<Teacher> selectElements();

    public void addElement(String name) throws SQLException;

    void updateElement(int id, String text);
}
