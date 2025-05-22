package scheduler.strategy;

import scheduler.model.Course;
import scheduler.model.Schedule;

public class MorningPreferenceConstraint implements ScheduleConstraint {
    @Override
    public boolean isSatisfied(Course course, Schedule currentSchedule) {
        String time = course.getPreferredTime().getTime();
        try {
            int hour = Integer.parseInt(time.split(":")[0]);
            return hour < 12;
        } catch (Exception e) {
            return false;
        }
    }
}
