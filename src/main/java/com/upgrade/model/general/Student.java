package com.upgrade.model.general;

import java.util.LinkedList;

public class Student {
    private static LinkedList<Student> students = new LinkedList<Student>();

    private Section section;
    private String firstNameZybooks;
    private String lastNameZybooks;
    private String idZybooks;
    private String firstNameBrightSpace;
    private String lastNameBrightSpace;
    private String usernameBrightSpace;

    //Getters/Setters Below

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
