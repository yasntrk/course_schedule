package scheduler.strategy;

import scheduler.model.Course;
import scheduler.model.Schedule;

public interface ScheduleConstraint {
    boolean isSatisfied(Course course, Schedule currentSchedule);
}
