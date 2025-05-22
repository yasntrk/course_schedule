package scheduler.model;

public class Course {
    private String code;
    private Lecturer lecturer;
    private TimeSlot preferredTime;

    public Course(String code, Lecturer lecturer, TimeSlot preferredTime) {
        this.code = code;
        this.lecturer = lecturer;
        this.preferredTime = preferredTime;
    }

    public String getCode() {
        return code;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public TimeSlot getPreferredTime() {
        return preferredTime;
    }

    @Override
    public String toString() {
        return code + " - " + preferredTime;
    }
}
