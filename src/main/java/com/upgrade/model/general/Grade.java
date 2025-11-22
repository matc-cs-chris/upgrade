package com.upgrade.model.general;

public class Grade {
    private Student student;
    private GradeCategory gradeCategory;
    private String assignmentName;
    private int totalPoints;
    private int pointsReceived;

    //Getters/Setters Below

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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getPointsReceived() {
        return pointsReceived;
    }

    public void setPointsReceived(int pointsReceived) {
        this.pointsReceived = pointsReceived;
    }
}
