package scheduler.factory;

import scheduler.model.Schedule;

public class UndergraduateSchedule extends Schedule {
   
    @Override
    public String toString() {
        return "Undergraduate Course Schedule:\n" + super.getScheduledCourses();
    }
}
