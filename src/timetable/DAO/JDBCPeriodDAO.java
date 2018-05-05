package timetable.DAO;

import timetable.entity.Period;

import java.sql.*;
import java.util.ArrayList;

public class JDBCPeriodDAO implements PeriodDAO {

    private Connection connection;

    public JDBCPeriodDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Period> selectElements() {
        ArrayList<Period> periods = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM period ORDER BY hour, minute ASC")){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int hour = rs.getInt(2);
                int minute = rs.getInt(3);
                periods.add(new Period(id, hour, minute));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return periods;
    }

    public void addElement(int hour, int minute) throws SQLException {
        ArrayList<Period> periods = selectElements();
        for (Period period : periods) {
            if (period.getHour() == hour && period.getMinute() == minute) {
                throw new SQLException("Je hebt deze periode al toegevoegd");
            }
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO period(hour, minute) VALUES (?, ?);")) {
            stmt.setInt(1, hour);
            stmt.setInt(2, minute);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
