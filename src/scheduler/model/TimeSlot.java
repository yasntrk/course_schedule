package scheduler.model;

public class TimeSlot {
    private String day;
    private String time;

    public TimeSlot(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return day + " " + time;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot)) return false;
        TimeSlot other = (TimeSlot) obj;
        return this.day.equals(other.day) && this.time.equals(other.time);
    }

    @Override
    public int hashCode() {
        return (day + time).hashCode();
    }
}
