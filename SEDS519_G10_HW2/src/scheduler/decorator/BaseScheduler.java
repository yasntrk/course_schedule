package scheduler.decorator;

import scheduler.model.Course;
import scheduler.model.Schedule;

public class BaseScheduler implements Scheduler {
    @Override
    public boolean schedule(Course course, Schedule schedule) {
        schedule.addCourse(course);
        return true;
    }
}
