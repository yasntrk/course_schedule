package scheduler.strategy;

import scheduler.model.Course;
import scheduler.model.Schedule;

public class NoOverlapConstraint implements ScheduleConstraint {
    @Override
    public boolean isSatisfied(Course course, Schedule currentSchedule) {
        return !currentSchedule.hasConflict(course.getPreferredTime());
    }
}
