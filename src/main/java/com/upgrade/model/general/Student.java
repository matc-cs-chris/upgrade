package com.upgrade.model.general;

import java.util.*;

public class Student {
    private static LinkedList<Student> students = new LinkedList<Student>();

    private Section section;
    private String firstNameZybooks;
    private String lastNameZybooks;
    private String idZybooks;
    private String firstNameBrightSpace;
    private String lastNameBrightSpace;
    private String usernameBrightSpace;
    private HashMap<GradeCategory, ArrayList<Grade>> categoryToGrades;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return Objects.equals(section, student.section) && Objects.equals(firstNameZybooks, student.firstNameZybooks) && Objects.equals(lastNameZybooks, student.lastNameZybooks) && Objects.equals(idZybooks, student.idZybooks) && Objects.equals(firstNameBrightSpace, student.firstNameBrightSpace) && Objects.equals(lastNameBrightSpace, student.lastNameBrightSpace) && Objects.equals(usernameBrightSpace, student.usernameBrightSpace) && Objects.equals(categoryToGrades, student.categoryToGrades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, firstNameZybooks, lastNameZybooks, idZybooks, firstNameBrightSpace, lastNameBrightSpace, usernameBrightSpace, categoryToGrades);
    }

    //Getters/Setters Below

    public HashMap<GradeCategory, ArrayList<Grade>> getCategoryToGrades() {
        return categoryToGrades;
    }

    public void setCategoryToGrades(HashMap<GradeCategory, ArrayList<Grade>> categoryToGrades) {
        this.categoryToGrades = categoryToGrades;
    }

    public static LinkedList<Student> getStudents() {
        return students;
    }

    public static void setStudents(LinkedList<Student> students) {
        Student.students = students;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getFirstNameZybooks() {
        return firstNameZybooks;
    }

    public void setFirstNameZybooks(String firstNameZybooks) {
        this.firstNameZybooks = firstNameZybooks;
    }

    public String getLastNameZybooks() {
        return lastNameZybooks;
    }

    public void setLastNameZybooks(String lastNameZybooks) {
        this.lastNameZybooks = lastNameZybooks;
    }

    public String getIdZybooks() {
        return idZybooks;
    }

    public void setIdZybooks(String idZybooks) {
        this.idZybooks = idZybooks;
    }

    public String getFirstNameBrightSpace() {
        return firstNameBrightSpace;
    }

    public void setFirstNameBrightSpace(String firstNameBrightSpace) {
        this.firstNameBrightSpace = firstNameBrightSpace;
    }

    public String getLastNameBrightSpace() {
        return lastNameBrightSpace;
    }

    public void setLastNameBrightSpace(String lastNameBrightSpace) {
        this.lastNameBrightSpace = lastNameBrightSpace;
    }

    public String getUsernameBrightSpace() {
        return usernameBrightSpace;
    }

    public void setUsernameBrightSpace(String usernameBrightSpace) {
        this.usernameBrightSpace = usernameBrightSpace;
    }
}
