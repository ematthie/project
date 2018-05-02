package timetable.DAO;

import timetable.entity.Period;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCPeriodDAO implements PeriodDAO {

    private Connection connection;

    public JDBCPeriodDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Period> selectElements() {
        ArrayList<Period> periods = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM period")){
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
}
