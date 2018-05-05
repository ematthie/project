package timetable.DAO;

import timetable.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO {

    public ArrayList<Student> selectElements();

    public void addElement(String naam) throws SQLException;

}
