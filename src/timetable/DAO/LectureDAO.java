package timetable.DAO;

import timetable.entity.Lecture;

import java.util.ArrayList;

public interface LectureDAO {

    public ArrayList<Lecture> selectElementsByRichting(int value);

    public ArrayList<Lecture> selectElementsByLocatie(int value);

    public ArrayList<Lecture> selectElementsByTeacher(int value);

}
