package scheduler.decorator;

import scheduler.model.Course;
import scheduler.model.Schedule;

public class ValidationDecorator implements Scheduler {
    private Scheduler inner;

    public ValidationDecorator(Scheduler inner) {
        this.inner = inner;
    }

    @Override
    public boolean schedule(Course course, Schedule schedule) {
        if (course.getCode() == null || course.getPreferredTime() == null) {
            System.out.println("Invalid Course: " + course);
            return false;
        }
        return inner.schedule(course, schedule);
    }
}
