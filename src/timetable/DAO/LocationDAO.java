package timetable.DAO;

import timetable.entity.Location;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LocationDAO {

    public ArrayList<Location> selectElements();

    public void addElement(String naam) throws SQLException;

    void updateElement(int id, String text);
}
