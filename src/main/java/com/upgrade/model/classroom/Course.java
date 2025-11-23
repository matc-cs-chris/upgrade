package com.upgrade.model.classroom;

import com.upgrade.config.CourseConfig;

import java.util.LinkedList;

public class Course {
    private static LinkedList<Course> courses;

    private String name;
    private LinkedList<Section> sections;
    private CourseConfig courseConfig;

    //Getters/Setters Below

    public static LinkedList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(LinkedList<Course> courses) {
        Course.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Section> getSections() {
        return sections;
    }

    public void setSections(LinkedList<Section> sections) {
        this.sections = sections;
    }

    public CourseConfig getCourseConfig() {
        return courseConfig;
    }

    public void setCourseConfig(CourseConfig courseConfig) {
        this.courseConfig = courseConfig;
    }
}
