package com.upgrade.model.general;

import com.upgrade.config.CourseConfig;
import com.upgrade.helpers.ExcelHelper;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelTable {
    Course course;
    ArrayList<String[]> rowColumnValues;
    int width;
    int height;

    int[] receivedPointsIndex;
    int[] possiblePointsIndex;
    int[] percentageGradeIndex;

    int individualGradesStartIndex;
    int[] gradeCategoryStartIndex;
    int[] gradeCategoryEndIndex;

    GradeCategory[] gradeCategories;

    int currentRow;

    public ExcelTable(Course course) {
        this.course = course;
        rowColumnValues = new ArrayList<>();

        rowColumnValues.add(getHeaderColumn());
    }

    private String[] getHeaderColumn() {
        CourseConfig config = course.getCourseConfig();
        gradeCategories = config.getGradeCategories().toArray(
                new GradeCategory[config.getGradeCategories().size()]);

        width = getMapDimensions(Grade.getAllCategoriesToNames()) + gradeCategories.length * 4 + 10;
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
            for(int i = 0; i < rowColumnValues.size(); i++) {
                out.println(ExcelHelper.getRowText(rowColumnValues.get(i)));
            }
        }
        catch(Exception e) {
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
