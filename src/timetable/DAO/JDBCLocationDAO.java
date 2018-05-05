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
    public void addElement(String name) throws SQLException {
        ArrayList<Location> locations = selectElements();
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                throw new SQLException("Je hebt deze teacher al toegevoegd");
            }
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO location(name) VALUES (?);")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
