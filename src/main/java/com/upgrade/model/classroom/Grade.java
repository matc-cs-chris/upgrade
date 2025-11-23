package com.upgrade.model.classroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Grade {
    private static HashMap<GradeCategory, ArrayList<String>> allCategoriesToNames;

    private Student student;
    private GradeCategory gradeCategory;
    private String assignmentName;
    private double totalPoints;
    private double percentageReceived;
    private double pointsReceived;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grade grade)) return false;
        return Double.compare(totalPoints, grade.totalPoints) == 0 && Double.compare(percentageReceived, grade.percentageReceived) == 0 && Double.compare(pointsReceived, grade.pointsReceived) == 0 && Objects.equals(gradeCategory, grade.gradeCategory) && Objects.equals(assignmentName, grade.assignmentName);
    }

    @Override
    public int hashCode() { //adding student would be circular
        return Objects.hash(gradeCategory, assignmentName, totalPoints, percentageReceived, pointsReceived);
    }

//Getters/Setters Below
    public double getPercentageReceived() {
        return percentageReceived;
    }

    public void setPercentageReceived(double percentageReceived) {
        this.percentageReceived = percentageReceived;
    }

    public double getPointsReceived() {
        return pointsReceived;
    }

    public void setPointsReceived(double pointsReceived) {
        this.pointsReceived = pointsReceived;
    }

    public static HashMap<GradeCategory, ArrayList<String>> getAllCategoriesToNames() {
        return allCategoriesToNames;
    }

    public static void setAllCategoriesToNames(HashMap<GradeCategory, ArrayList<String>> allCategoriesToNames) {
        Grade.allCategoriesToNames = allCategoriesToNames;
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
}
