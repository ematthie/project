package timetable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDataAccesProvider implements DataAccesProvider {

    private String url;

    public JDBCDataAccesProvider(String url) {
        this.url = url;
    }

    public java.sql.Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public DataAccesContext getDataAccessContext() throws SQLException {
        try {
            return new JDBCDataAccesContext(getConnection());
        } catch (SQLException ex) {
            throw new SQLException("Could not create data access context", ex);
        }
    }
}