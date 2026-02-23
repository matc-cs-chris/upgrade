package com.upgrade.model.excel;

import com.upgrade.config.CourseConfig;
import com.upgrade.helpers.ExcelHelper;
import com.upgrade.model.classroom.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelTable {
    Course course;
    ArrayList<String[]> rowColumnValues;
    ArrayList<String[]> rowColumnFullText;
    int width;
    int height;

    int[] receivedPointsIndex;
    int[] possiblePointsIndex;
    int[] percentageGradeIndex;
    int[] adjustedReceivedPointsIndex;
    int[] adjustedPossiblePointsIndex;
    int[] adjustedPercentageGradeIndex;

    int individualGradesStartIndex;
    int[] gradeCategoryStartIndex;
    int[] gradeCategoryEndIndex;

    GradeCategory[] gradeCategories;

    public ExcelTable(Course course, boolean usePercentages, int numDropMinGrades) {
        this.course = course;
        rowColumnValues = new ArrayList<>();
        rowColumnFullText = new ArrayList<>();

        rowColumnValues.add(getHeaderColumn());
        rowColumnFullText.add(new String[width]);

        for (Section section : course.getSections()) {
            String[] sectionHeaderRow = new String[width];
            sectionHeaderRow[0] = section.getName();

            rowColumnValues.add(new String[width]);
            rowColumnValues.add(sectionHeaderRow);
            rowColumnValues.add(new String[width]);

            rowColumnFullText.add(new String[width]);
            rowColumnFullText.add(new String[width]);
            rowColumnFullText.add(new String[width]);

            for(Student student : section.getStudents()) {
                String[] rows = new String[width];
                String[] rowComments = new String[width];

                rows[0] = student.getUsernameBrightSpace();
                rows[1] = student.getIdZybooks();
                rows[2] = student.getLastNameZybooks();
                rows[3] = student.getFirstNameZybooks();

                for(int categoryNumber = 0; categoryNumber < gradeCategories.length; categoryNumber++) {
                    GradeCategory gradeCategory = gradeCategories[categoryNumber];

                    int currentColumnIndex = gradeCategoryStartIndex[categoryNumber];
                    double totalPoints = 0;
                    double receivedPoints = 0;
                    double adjustedPoints = 0;

                    if(student.getCategoryToGrades() == null) continue; //gets rid of demo student

                    ArrayList<Grade> grades = student.getCategoryToGrades().get(gradeCategory);

                    ArrayList<Grade> droppedGrades = getDroppedGrades(grades, numDropMinGrades);

                    for(int gradeNumber = 0; gradeNumber < grades.size(); gradeNumber++) {
                        Grade grade = grades.get(gradeNumber);

                        if(droppedGrades.contains(grade)) {
                            if (usePercentages) {
                                rowComments[currentColumnIndex] =
                                        " (was " + Double.toString(grade.getPercentageReceived()) + ")";
                                rows[currentColumnIndex] = "100.0" + rowComments[currentColumnIndex];
                            } else {
                                rowComments[currentColumnIndex] =
                                        " (was " + Double.toString(grade.getPointsReceived()) + ")";
                                rows[currentColumnIndex] = Double.toString(grade.getTotalPoints()) +
                                        rowComments[currentColumnIndex];
                            }

                            adjustedPoints += grade.getTotalPoints();
                            droppedGrades.remove(grade); //minor optimization
                        }
                        else {
                            if (usePercentages) {
                                rows[currentColumnIndex] = Double.toString(grade.getPercentageReceived());
                                rowComments[currentColumnIndex] = Double.toString(grade.getPercentageReceived());
                            } else {
                                rows[currentColumnIndex] = Double.toString(grade.getPointsReceived());
                                rowComments[currentColumnIndex] = Double.toString(grade.getPointsReceived());
                            }

                            adjustedPoints += grade.getPointsReceived();
                        }

                        totalPoints += grade.getTotalPoints();
                        receivedPoints += grade.getPointsReceived();



                        currentColumnIndex++;
                    }

                    rows[receivedPointsIndex[categoryNumber]] = Double.toString(receivedPoints);
                    rows[possiblePointsIndex[categoryNumber]] = Double.toString(totalPoints);
                    rows[percentageGradeIndex[categoryNumber]] = Double.toString(receivedPoints*100.0/totalPoints);

                    rows[adjustedReceivedPointsIndex[categoryNumber]] = Double.toString(adjustedPoints);
                    rows[adjustedPossiblePointsIndex[categoryNumber]] = Double.toString(totalPoints);
                    rows[adjustedPercentageGradeIndex[categoryNumber]] =
                            Double.toString(adjustedPoints*100.0/totalPoints);
                }

                rowColumnValues.add(rows);
                rowColumnFullText.add(rowComments);
            }
        }
    }

    private ArrayList<Grade> getDroppedGrades(ArrayList<Grade> grades, int numDropMinGrades) {
        ArrayList<Grade> droppedGrades = new ArrayList<Grade>();

        for (int gradeNumber = 0; gradeNumber < grades.size(); gradeNumber++) {
            Grade grade = grades.get(gradeNumber);

            double diffReceivedAndPossiblePoints = grade.getTotalPoints() - grade.getPointsReceived();

            for(int droppedGradesIndex = 0; droppedGradesIndex < numDropMinGrades; droppedGradesIndex++) {
                if(droppedGrades.size() < numDropMinGrades) {
                    droppedGrades.add(grade);
                    break;
                }

                Grade storedMaxGrade = droppedGrades.get(droppedGradesIndex);
                double storedGradeDiff =  storedMaxGrade.getTotalPoints() - storedMaxGrade.getPointsReceived();

                if(diffReceivedAndPossiblePoints > storedGradeDiff) {
                    droppedGrades.set(droppedGradesIndex, grade);
                    break;
                }
            }
        }

        return droppedGrades;
    }

    private String[] getHeaderColumn() {
        CourseConfig config = course.getCourseConfig();
        gradeCategories = config.getGradeCategories().toArray(
                new GradeCategory[config.getGradeCategories().size()]);

        width = getMapDimensions(Grade.getAllCategoriesToNames()) + gradeCategories.length * 4 + 20;
        String[] columns = new String[width];

        columns[0] = "BrightSpace Username";
        columns[1] = "Zybooks ID";
        columns[2] = "Zybooks Last Name";
        columns[3] = "Zybooks First Name";

        int currentColumnIndex = 5;
        int categoryIndex = 0;

        //START CALCULATED GRADES
        receivedPointsIndex = new int[gradeCategories.length];
        possiblePointsIndex = new int[gradeCategories.length];
        percentageGradeIndex = new int[gradeCategories.length];

        adjustedReceivedPointsIndex = new int[gradeCategories.length];
        adjustedPossiblePointsIndex = new int[gradeCategories.length];
        adjustedPercentageGradeIndex = new int[gradeCategories.length];

        for(GradeCategory gradeCategory : gradeCategories) {
            receivedPointsIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Received " + gradeCategory.getName() + " Points";
            currentColumnIndex++;

            possiblePointsIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Possible " + gradeCategory.getName() + " Points";
            currentColumnIndex++;

            percentageGradeIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Percentage " + gradeCategory.getName() + " Grade";
            currentColumnIndex++;
            currentColumnIndex++;

            categoryIndex++;
        }

        categoryIndex = 0;

        for(GradeCategory gradeCategory : gradeCategories) {
            adjustedReceivedPointsIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Adjusted " + gradeCategory.getName() + " Points";
            currentColumnIndex++;

            adjustedPossiblePointsIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Adjusted Possible " + gradeCategory.getName() + " Points";
            currentColumnIndex++;

            adjustedPercentageGradeIndex[categoryIndex] = currentColumnIndex;
            columns[currentColumnIndex] = "Adjusted Percentage " + gradeCategory.getName() + " Grade";
            currentColumnIndex++;
            currentColumnIndex++;

            categoryIndex++;
        }

        individualGradesStartIndex = currentColumnIndex;

        //START INDIVIDUAL GRADES
        gradeCategoryStartIndex = new int[gradeCategories.length];
        gradeCategoryEndIndex = new int[gradeCategories.length];

        for(int gradeCategoryIndex = 0; gradeCategoryIndex < gradeCategories.length; gradeCategoryIndex++) {
            GradeCategory gradeCategory = gradeCategories[gradeCategoryIndex];
            ArrayList<String> gradeNames = Grade.getAllCategoriesToNames().get(gradeCategory);

            gradeCategoryStartIndex[gradeCategoryIndex] = currentColumnIndex;
            for(int i = 0; i < gradeNames.size(); i++) {
                columns[currentColumnIndex] = gradeNames.get(i);
                currentColumnIndex++;
            }
            gradeCategoryEndIndex[gradeCategoryIndex] = currentColumnIndex;

            currentColumnIndex++;
        }

        return columns;
    }

    public void write(File outFile) {
        try(PrintWriter out = new PrintWriter(outFile)) {
            for(int i = 0; i < rowColumnFullText.size(); i++) {
                out.println(ExcelHelper.getRowText(rowColumnValues.get(i)));
            }
        }
        catch(IOException e) {
            System.out.println("Something went wrong writing the table");
            System.exit(1);
        }
    }

    private static <K,E> int getMapDimensions(HashMap<K,ArrayList<E>> map) {
        int count = 0;

        for(K key : map.keySet()) {
            count += map.get(key).size();
        }

        return count;
    }


}
