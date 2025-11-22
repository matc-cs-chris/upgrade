package com.upgrade.model.general;

import java.util.ArrayList;

public class Grade {
    private static ArrayList<String> allNames;

    private Student student;
    private GradeCategory gradeCategory;
    private String assignmentName;
    private double totalPoints;
    private double percentagePointsReceived;

    //Getters/Setters Below


    public static ArrayList<String> getAllNames() {
        return allNames;
    }

    public static void setAllNames(ArrayList<String> allNames) {
        Grade.allNames = allNames;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public GradeCategory getGradeCategory() {
        return gradeCategory;
    }

    public void setGradeCategory(GradeCategory gradeCategory) {
        this.gradeCategory = gradeCategory;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getPercentagePointsReceived() {
        return percentagePointsReceived;
    }

    public void setPercentagePointsReceived(double percentagePointsReceived) {
        this.percentagePointsReceived = percentagePointsReceived;
    }
}
