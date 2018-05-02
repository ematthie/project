package timetable;

import java.sql.SQLException;

public interface DataAccesProvider {

    public DataAccesContext getDataAccessContext() throws SQLException;

}
