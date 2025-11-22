package com.upgrade.config;

import com.upgrade.model.general.Course;
import com.upgrade.model.general.GradeCategory;

import java.util.ArrayList;

public class CourseConfig {
    private ArrayList<GradeCategory> gradeCategories;
    private String gradeStartColumnZybooks;
    private String lastNameColumnZybooks;
    private String firstNameColumnZybooks;
    private String idColumnZybooks;
    private Course course;

    //Getters/Setters below

    public ArrayList<GradeCategory> getGradeCategories() {
        return gradeCategories;
    }

    public void setGradeCategories(ArrayList<GradeCategory> gradeCategories) {
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
