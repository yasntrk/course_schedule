package scheduler.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Course> scheduledCourses = new ArrayList<>();

    public void addCourse(Course course) {
        scheduledCourses.add(course);
    }

    public List<Course> getScheduledCourses() {
        return scheduledCourses;
    }

    public boolean hasConflict(TimeSlot timeSlot) {
        for (Course course : scheduledCourses) {
            if (course.getPreferredTime().equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }
}
