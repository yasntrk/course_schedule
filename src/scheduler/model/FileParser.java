package scheduler.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {

    public static Lecturer parseLecturerFromFile(String filename, String lecturerName) {
        Lecturer lecturer = new Lecturer(lecturerName);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 3) {
                    String courseCode = parts[0].trim();
                    String day = parts[1].trim();
                    String time = parts[2].trim();

                    TimeSlot timeSlot = new TimeSlot(day, time);
                    Course course = new Course(courseCode, lecturer, timeSlot);

                    lecturer.addCourse(course);
                } else {
                    System.out.println("Invalid Line Format: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("File is not readable: " + e.getMessage());
        }

        return lecturer;
    }
}
