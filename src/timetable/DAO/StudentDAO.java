package timetable.DAO;

import timetable.entity.Student;

import java.util.ArrayList;

public interface StudentDAO {

    public ArrayList<Student> selectElements();

    public void addElement(String naam);

}
