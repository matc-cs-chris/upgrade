package com.upgrade.config;

import com.upgrade.model.classroom.Course;
import com.upgrade.model.classroom.GradeCategory;

import java.util.LinkedList;

public class CourseConfig {
    private LinkedList<GradeCategory> gradeCategories;
    private String gradeStartColumnZybooks;
    private String lastNameColumnZybooks;
    private String firstNameColumnZybooks;
    private String idColumnZybooks;
    private Course course;

    //Getters/Setters below

    public LinkedList<GradeCategory> getGradeCategories() {
        return gradeCategories;
    }

    public void setGradeCategories(LinkedList<GradeCategory> gradeCategories) {
        this.gradeCategories = gradeCategories;
    }

    public String getGradeStartColumnZybooks() {
        return gradeStartColumnZybooks;
    }

    public void setGradeStartColumnZybooks(String gradeStartColumnZybooks) {
        this.gradeStartColumnZybooks = gradeStartColumnZybooks;
    }

    public String getLastNameColumnZybooks() {
        return lastNameColumnZybooks;
    }

    public void setLastNameColumnZybooks(String lastNameColumnZybooks) {
        this.lastNameColumnZybooks = lastNameColumnZybooks;
    }

    public String getFirstNameColumnZybooks() {
        return firstNameColumnZybooks;
    }

    public void setFirstNameColumnZybooks(String firstNameColumnZybooks) {
        this.firstNameColumnZybooks = firstNameColumnZybooks;
    }

    public String getIdColumnZybooks() {
        return idColumnZybooks;
    }

    public void setIdColumnZybooks(String idColumnZybooks) {
        this.idColumnZybooks = idColumnZybooks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
