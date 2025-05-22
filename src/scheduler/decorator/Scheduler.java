package scheduler.decorator;

import scheduler.model.Course;
import scheduler.model.Schedule;

public interface Scheduler {
    boolean schedule(Course course, Schedule schedule);
}
