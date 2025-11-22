package com.upgrade.model.general;

import com.upgrade.config.SectionConfig;

import java.util.ArrayList;
import java.util.LinkedList;

public class Section {
    private static LinkedList<Section> sections = new LinkedList<Section>();

    private Course course;
    private String name;
    private LinkedList<Student> students;
    private SectionConfig sectionConfig;

    //Getters/Setters Below

    public static LinkedList<Section> getSections() {
        return sections;
    }

    public static void setSections(LinkedList<Section> sections) {
        Section.sections = sections;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedList<Student> students) {
        this.students = students;
    }

    public SectionConfig getSectionConfig() {
        return sectionConfig;
    }

    public void setSectionConfig(SectionConfig sectionConfig) {
        this.sectionConfig = sectionConfig;
    }
}
