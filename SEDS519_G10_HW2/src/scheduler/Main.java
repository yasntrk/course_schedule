package scheduler;

import scheduler.decorator.*;
import scheduler.factory.*;
import scheduler.model.*;
import scheduler.strategy.*;
import scheduler.ui.ScheduleViewer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> filenames = Arrays.asList(
                "src/OGokalp.txt",
                "src/AYilmaz.txt",
                "src/EInan.txt",
                "src/BErgenc.txt"
        );

        List<String> lecturerNames = Arrays.asList("OGokalp", "AYilmaz", "EInan", "BErgenc");

        ScheduleFactory factory = new ScheduleFactory() {
            @Override
            public Schedule createSchedule() {
                return new UndergraduateSchedule();
            }
        };

        Schedule schedule = factory.createSchedule();
        ScheduleConstraint constraint = new NoOverlapConstraint();

        Scheduler scheduler = new ValidationDecorator(
                new LoggingDecorator(
                        new BaseScheduler()));

        List<Course> rejectedCourses = new ArrayList<>();
        List<String> rejectionReasons = new ArrayList<>();

        for (int i = 0; i < filenames.size(); i++) {
            Lecturer lecturer = FileParser.parseLecturerFromFile(filenames.get(i), lecturerNames.get(i));

            for (Course course : lecturer.getCourses()) {
                int courseCodeNumber = Integer.parseInt(course.getCode().replaceAll("\\D+", ""));
                boolean isGraduate = courseCodeNumber >= 500;

                if (isGraduate) {
                    System.out.println("[GRADUATE] " + course);
                } else {
                    System.out.println("[UNDERGRADUATE] " + course);
                }

                if (constraint.isSatisfied(course, schedule)) {
                    scheduler.schedule(course, schedule);
                } else {
                    rejectedCourses.add(course);
                    rejectionReasons.add("Time Conflict");
                    System.out.println("Time Conflict: " + course);
                }
            }
        }

        ScheduleViewer.exportToHtml(schedule, "schedule.html", rejectedCourses, rejectionReasons);
    }
}
