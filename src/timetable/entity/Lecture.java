package timetable.entity;

public class Lecture {

    private int studentsID;
    private int teacherID;
    private int locationID;
    private String course;
    private int day;
    private int start;
    private int duration;

    public Lecture(int studentsID, int teacherID, int locationID, String course, int day, int start, int duration) {
        this.studentsID = studentsID;
        this.teacherID = teacherID;
        this.locationID = locationID;
        this.course = course;
        this.day = day;
        this.start = start;
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public int getDay() {
        return day;
    }

    public int getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return course;
    }

    public int getStudentsID() {
        return studentsID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getLocationID() {
        return locationID;
    }
}
