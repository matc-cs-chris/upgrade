package com.upgrade.operations;

import com.upgrade.model.classroom.Course;
import com.upgrade.model.excel.ExcelTable;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CourseGradesWriter {
    //TODO
    public static void writeGradesFile(File outFile, String courseName) {
        String saveFileName = outFile.getAbsolutePath();
        saveFileName += File.separator;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String newFileName = LocalDateTime.now().format(dtf);
        newFileName += "_" + courseName + "_Grades.csv";

        //just in case
        newFileName = newFileName.replaceAll("/","_");
        newFileName = newFileName.replaceAll(" ","_");
        newFileName = newFileName.replaceAll(":","-");

        saveFileName += newFileName;

        outFile = new File(saveFileName);

        Course course = null;

        for (Course aCourse : Course.getCourses()) {
            if(aCourse.getName().equals(courseName)) course = aCourse;
        }

        assert course != null;

        //TODO - form and print excel table

        ExcelTable table = new ExcelTable(course);
        table.write(outFile);

        System.out.println("Course " + courseName + " has been written to: " + outFile.getAbsolutePath());
    }
}
