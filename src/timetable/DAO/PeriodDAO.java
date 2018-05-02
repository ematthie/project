package timetable.DAO;

import timetable.entity.Period;

import java.util.ArrayList;

public interface PeriodDAO {

    public ArrayList<Period> selectElements();
}
