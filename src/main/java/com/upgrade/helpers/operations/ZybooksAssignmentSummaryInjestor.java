package com.upgrade.helpers.operations;

import com.upgrade.helpers.ExcelHelper;
import com.upgrade.helpers.RegexHelper;
import com.upgrade.helpers.SearchHelper;
import com.upgrade.model.general.Course;
import com.upgrade.model.general.Grade;
import com.upgrade.model.general.GradeCategory;
import com.upgrade.model.general.Student;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ZybooksAssignmentSummaryInjestor {
    public static void parseGrades(Course course, String endColumnText,
                                   File zybooksAssignmentsSummaryFile, File outFile) {
            String[] headerColumns = null;
            String[] assignmentNames = null;
            int[] totalPoints = null;

            try (Scanner scanner = new Scanner(zybooksAssignmentsSummaryFile))
            {
                //PROCESS HEADERS
                String headerRow = scanner.nextLine();

                headerColumns = headerRow.split(",");
                assignmentNames = RegexHelper.getAssignmentNames(headerColumns);
                totalPoints = RegexHelper.getTotalPoints(headerColumns);

                //SETUP CONFIG
                String idColumnText = course.getCourseConfig().getIdColumnZybooks();
                int idColumn = ExcelHelper.columnToIndex(idColumnText);
                String firstNameText = course.getCourseConfig().getFirstNameColumnZybooks();
                int firstNameColumn = ExcelHelper.columnToIndex(firstNameText);
                String lastNameText = course.getCourseConfig().getLastNameColumnZybooks();
                int lastNameColumn = ExcelHelper.columnToIndex(lastNameText);
                String startText = course.getCourseConfig().getGradeStartColumnZybooks();
                int startColumn = ExcelHelper.columnToIndex(startText);
                int endColumn = ExcelHelper.columnToIndex(endColumnText);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");

                    LinkedList<Student> students = Student.getStudents();
                    Student studentMatch = SearchHelper.linearSearch(Student.getStudents(),
                            s -> s.getIdZybooks().equals(columns[idColumn]));

                    if (studentMatch != null) { //means is in xml as a student
                        studentMatch.setFirstNameZybooks(columns[firstNameColumn]);
                        studentMatch.setLastNameZybooks(columns[lastNameColumn]);

                        //RECORD GRADES
                        createGrades(studentMatch, assignmentNames, totalPoints, columns, startColumn, endColumn);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error processing zybooks assignments summary file");
                System.exit(1);
            }

    }

    private static void createGrades(Student studentMatch, String[] gradeNames,
                                     int[] totalPoints, String[] columns, int startColumn, int endColumn) {

        if(Grade.getAllNames() == null) Grade.setAllNames(new ArrayList<>());
        if(studentMatch.getGrades() == null) studentMatch.setGrades(new ArrayList<>());

        for(int i = startColumn; i <= endColumn; i++) {
            String gradeName = gradeNames[i];
            int totalPoint = totalPoints[i];

            if(gradeName != null) {
                Grade.getAllNames().add(gradeName);

                Grade grade = new Grade();
                grade.setStudent(studentMatch);
                grade.setAssignmentName(gradeName);
                grade.setTotalPoints(totalPoint);

                try {
                    grade.setPercentagePointsReceived(Double.parseDouble(columns[i]));
                }
                catch (NumberFormatException e) {
                    grade.setPercentagePointsReceived(Double.NaN);
                    System.out.println(studentMatch.getUsernameBrightSpace() + "'s " + gradeName +
                            " score of: " + columns[i] + " is not a number");
                    System.exit(1);
                }

                LinkedList<GradeCategory> gradeCategories =
                        studentMatch.getSection().getCourse().getCourseConfig().getGradeCategories();

                for(GradeCategory gradeCategory : gradeCategories) {
                    if (gradeName.contains(gradeCategory.getName())) {
                        grade.setGradeCategory(gradeCategory);
                    }
                }

                studentMatch.getGrades().add(grade);

                assert grade.getGradeCategory() != null;
            }
        }
    }
}
