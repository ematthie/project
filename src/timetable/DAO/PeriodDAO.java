package timetable.DAO;

import timetable.entity.Period;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PeriodDAO {

    public ArrayList<Period> selectElements();

    public void addElement(int hour, int minute) throws SQLException;
}
