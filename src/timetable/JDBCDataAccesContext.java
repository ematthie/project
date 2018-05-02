package timetable;

import timetable.DAO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDataAccesContext implements DataAccesContext {

    private Connection connection;

    public JDBCDataAccesContext(Connection connection) throws SQLException{
        this.connection = connection;
    }

    @Override
    public JDBCPeriodDAO getPeriodDAO() {
        return new JDBCPeriodDAO(connection);
    }

    @Override
    public JDBCLectureDAO getLectureDAO() {
        return new JDBCLectureDAO(connection);
    }

    @Override
    public JDBCLocationDAO getLocationDAO() {
        return new JDBCLocationDAO(connection);
    }

    @Override
    public JDBCStudentDAO getStudentDAO() {
        return new JDBCStudentDAO(connection);
    }

    @Override
    public JDBCTeacherDAO getTeacherDAO() {
        return new JDBCTeacherDAO(connection);
    }

    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new SQLException("Could not close context", ex);
        }
    }
}
