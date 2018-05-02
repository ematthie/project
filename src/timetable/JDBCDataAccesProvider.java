package timetable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDataAccesProvider implements DataAccesProvider, InvalidationListener {

    private Model model;

    public JDBCDataAccesProvider(Model model) {
        this.model = model;
    }

    public java.sql.Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(model.getUrl());
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

    @Override
    public void invalidated(Observable observable) {

    }
}
