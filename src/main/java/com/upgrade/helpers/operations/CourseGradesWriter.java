package com.upgrade.helpers.operations;

import com.upgrade.model.general.*;

import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;

public class CourseGradesWriter {
    //TODO
    public static void writeGradesFile(File outFile, String courseName) {
        String saveFileName = outFile.getAbsolutePath();
        saveFileName += File.separator;

        String newFileName = (new Timestamp(Instant.now().getEpochSecond())).toString();;
        newFileName += "_" + courseName + "_Grades.csv";
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
    }
}
