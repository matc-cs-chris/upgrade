package com.upgrade.model.general;

import com.upgrade.config.CourseConfig;

import java.util.ArrayList;

public class Course {
    private static ArrayList<Course> courses;

    private String name;
    private ArrayList<Section> sections;
    private CourseConfig courseConfig;

    //Getters/Setters Below

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(ArrayList<Course> courses) {
        Course.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public CourseConfig getCourseConfig() {
        return courseConfig;
    }

    public void setCourseConfig(CourseConfig courseConfig) {
        this.courseConfig = courseConfig;
    }
}
