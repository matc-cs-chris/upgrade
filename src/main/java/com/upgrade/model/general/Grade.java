package com.upgrade.model.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class Grade {
    private static HashMap<GradeCategory, ArrayList<String>> allCategoriesToNames;

    private Student student;
    private GradeCategory gradeCategory;
    private String assignmentName;
    private double totalPoints;
    private double percentagePointsReceived;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grade grade)) return false;
        return Double.compare(totalPoints, grade.totalPoints) == 0 && Double.compare(percentagePointsReceived, grade.percentagePointsReceived) == 0 && Objects.equals(gradeCategory, grade.gradeCategory) && Objects.equals(assignmentName, grade.assignmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeCategory, assignmentName, totalPoints, percentagePointsReceived);
    }

    //Getters/Setters Below

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

    public double getPercentagePointsReceived() {
        return percentagePointsReceived;
    }

    public void setPercentagePointsReceived(double percentagePointsReceived) {
        this.percentagePointsReceived = percentagePointsReceived;
    }
}
