package timetable.DAO;

import timetable.entity.Teacher;

import java.util.ArrayList;

public interface TeacherDAO {

    public ArrayList<Teacher> selectElements();

    public void addElement(String name);

}
