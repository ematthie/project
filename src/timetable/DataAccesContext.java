package timetable;

import timetable.DAO.*;

public interface DataAccesContext extends AutoCloseable {

    JDBCPeriodDAO getPeriodDAO();

    JDBCLectureDAO getLectureDAO();

    JDBCLocationDAO getLocationDAO();

    JDBCStudentDAO getStudentDAO();

    JDBCTeacherDAO getTeacherDAO();

}
