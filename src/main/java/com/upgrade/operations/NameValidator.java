package com.upgrade.operations;

import com.upgrade.model.classroom.Course;
import com.upgrade.model.classroom.Section;
import com.upgrade.model.classroom.Student;

public class NameValidator {
    public static boolean validateNames(Course course) {
        boolean errorDetected = false;

        for(Section section: course.getSections()) {
            for(Student student: section.getStudents()) {
                if(student.getLastNameZybooks() == null || student.getFirstNameZybooks() == null) continue;

                String usernameBrightspace =
                        student.getUsernameBrightSpace().replaceAll("#", "").toUpperCase();

                String firstLetterLastNameZybooks =
                        student.getLastNameZybooks().substring(0, 1).toUpperCase().replaceAll("\\s+","");
                String firstLetterLastNameBrightSpace =
                        usernameBrightspace.substring(0, 1).toUpperCase().replaceAll("\\s+","");;

                String firstNameZybooks =
                        student.getFirstNameZybooks().toUpperCase().replaceAll("\\s+","");;
                String firstNameBrightSpace =
                        usernameBrightspace.substring(1).toUpperCase().replaceAll("\\s+","");;

                if(!firstNameBrightSpace.contains(firstNameZybooks)) {
                    System.out.println("Possible mismatch for " + student.getLastNameZybooks() + ", " +
                            student.getFirstNameZybooks() + ":\n" + "First name zybooks: " +
                            firstNameZybooks + "\nFirst name Brightspace: " + firstNameBrightSpace + "\n");
                    errorDetected = true;
                }

                if(!firstLetterLastNameBrightSpace.equals(firstLetterLastNameZybooks)) {
                    System.out.println("Possible mismatch for " + student.getLastNameZybooks() + ", " +
                            student.getFirstNameZybooks() + ":\n" + "Last name zybooks starts with: " +
                            firstLetterLastNameZybooks + "\nLast name Brightspace starts with: " +
                            firstLetterLastNameBrightSpace + "\n");
                    errorDetected = true;
                }
            }
        }

        return errorDetected;
    }
}
