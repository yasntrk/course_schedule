package scheduler.decorator;

import scheduler.model.Course;
import scheduler.model.Schedule;

public class LoggingDecorator implements Scheduler {
    private Scheduler inner;

    public LoggingDecorator(Scheduler inner) {
        this.inner = inner;
    }

    @Override
    public boolean schedule(Course course, Schedule schedule) {
        System.out.println("Adding Course: " + course);
        return inner.schedule(course, schedule);
    }
}
