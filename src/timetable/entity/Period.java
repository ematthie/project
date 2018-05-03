package timetable.entity;

public class Period {

    private int id;
    private int hour;
    private int minute;

    public Period(int id, int hour, int minute) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        String string = hour + ":" + minute;
        if (minute < 10) {
            string += '0';
        }
        return string;
    }
}
