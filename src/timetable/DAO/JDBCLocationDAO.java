package timetable.DAO;

import timetable.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCLocationDAO implements LocationDAO {

    private Connection connection;

    public JDBCLocationDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Location> selectElements() {
        ArrayList<Location> locations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM location")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                locations.add(new Location(id, name));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return locations;
    }

    @Override
    public void addElement(String naam) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO location(name) VALUES (?);")) {
            stmt.setString(1, naam);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
