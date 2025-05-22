package scheduler.model;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Lecturer(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
