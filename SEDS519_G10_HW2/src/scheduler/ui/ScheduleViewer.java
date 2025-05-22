package scheduler.ui;

import scheduler.model.Course;
import scheduler.model.Schedule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ScheduleViewer {

    private static final List<String> DAYS = Arrays.asList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    );

    private static final List<String> TIME_SLOTS = Arrays.asList(
            "08:00", "09:00", "10:00", "11:00", "12:00",
            "13:00", "14:00", "15:00", "16:00", "17:00"
    );

    public static void exportToHtml(Schedule schedule, String filename,
                                     List<Course> rejectedCourses,
                                     List<String> rejectionReasons) {

        Map<String, Map<String, String>> slotMap = new LinkedHashMap<>();
        for (String time : TIME_SLOTS) {
            slotMap.put(time, new LinkedHashMap<>());
            for (String day : DAYS) {
                slotMap.get(time).put(day, "—");
            }
        }

        for (Course course : schedule.getScheduledCourses()) {
            String day = course.getPreferredTime().getDay();
            String time = course.getPreferredTime().getTime();
            String slot = time.split(":")[0] + ":00";

            if (!TIME_SLOTS.contains(slot)) continue;

            int codeNum = Integer.parseInt(course.getCode().replaceAll("\\D+", ""));
            boolean isGraduate = codeNum >= 500;

            String htmlLabel = "<strong>" + course.getCode() + "</strong><br>" +
                               course.getLecturer().getName();

            if (isGraduate) {
                htmlLabel = "<span style='color:red'>" + htmlLabel + "</span>";
            }

            slotMap.get(slot).put(day, htmlLabel);
        }

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset='UTF-8'>")
            .append("<title>Weekly Schedule</title>")
            .append("<style>")
            .append("table { border-collapse: collapse; width: 100%; }")
            .append("th, td { border: 1px solid black; padding: 8px; vertical-align: middle; text-align: center; }")
            .append("th { background-color: #f2f2f2; }")
            .append(".note-box { position: absolute; top: 10px; right: 10px; background: #ffe6e6; border: 1px solid red; padding: 10px; font-size: 12px; border-radius: 5px; }")
            .append("</style>")
            .append("</head><body>");

        html.append("<div class='note-box'><strong>Note:</strong> Graduate courses (code 500+) are shown in red.</div>");
        html.append("<h2>Weekly Schedule (Slot-Based)</h2>");
        html.append("<table><tr><th>Time</th>");
        for (String day : DAYS) {
            html.append("<th>").append(day).append("</th>");
        }
        html.append("</tr>");

        for (String time : TIME_SLOTS) {
            html.append("<tr><td><strong>").append(time).append("</strong></td>");
            for (String day : DAYS) {
                html.append("<td>").append(slotMap.get(time).get(day)).append("</td>");
            }
            html.append("</tr>");
        }

        html.append("</table>");

        html.append("<h3 style='color:darkred;'>Rejected Courses (Constraint Violations)</h3>");
        html.append("<table><tr><th>Course Code</th><th>Lecturer</th><th>Day</th><th>Time</th><th>Reason</th></tr>");
        for (int i = 0; i < rejectedCourses.size(); i++) {
            Course c = rejectedCourses.get(i);
            int codeNum = Integer.parseInt(c.getCode().replaceAll("\\D+", ""));
            boolean isGraduate = codeNum >= 500;

            html.append("<tr><td>");
            if (isGraduate) {
                html.append("<span style='color:red'><strong>")
                    .append(c.getCode())
                    .append("</strong></span>");
            } else {
                html.append("<strong>")
                    .append(c.getCode())
                    .append("</strong>");
            }

            html.append("</td><td>")
                .append(c.getLecturer().getName()).append("</td><td>")
                .append(c.getPreferredTime().getDay()).append("</td><td>")
                .append(c.getPreferredTime().getTime()).append("</td><td>")
                .append(rejectionReasons.get(i)).append("</td></tr>");
        }
        html.append("</table></body></html>");

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(html.toString());
            System.out.println("HTML schedule successfully written: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing HTML: " + e.getMessage());
        }
    }
}
