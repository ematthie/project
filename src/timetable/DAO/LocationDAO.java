package timetable.DAO;

import timetable.entity.Location;

import java.util.ArrayList;

public interface LocationDAO {

    public ArrayList<Location> selectElements();

    public void addElement(String naam);

}
