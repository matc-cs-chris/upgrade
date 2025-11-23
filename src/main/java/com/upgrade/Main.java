package com.upgrade;

import com.upgrade.helpers.FileHelper;
import com.upgrade.helpers.SearchHelper;
import com.upgrade.operations.ConfigIngestor;
import com.upgrade.operations.CourseGradesWriter;
import com.upgrade.operations.NameValidator;
import com.upgrade.operations.ZybooksAssignmentsIngestor;
import com.upgrade.model.classroom.Course;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: GUI? Maybe?
//        Application.launch(HelloApplication.class, args);

        Scanner scan = new Scanner(System.in);

        ConfigIngestor.parseCourses();

        System.out.println("Enter operation (or 'quit'): ");
        System.out.println("1 - Print Grade Summary");
//        System.out.println("2 - Print Grade Summary (One Student)");
//        System.out.println("3 - Validate Name Config");

        try{
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    printGradeSummary(scan);
                    break;
//TODO: Add single student reports?
                    //                case 2:
//                    printGradeSummary(scan, true);
//                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        catch(InputMismatchException ex){
            System.out.println("Invalid input");
        }


    }

    private static void printGradeSummary(Scanner consoleScanner) {
        boolean usePercentage = true;

        System.out.println("Choose: \n1 - Percentage\n2 - Points");
        switch (consoleScanner.next()) {
            case "1":
                usePercentage = true;
                break;
            case "2":
                usePercentage = false;
                break;
            default:
                usePercentage = true;
        }

        System.out.println("Enter End Column Letter Code: ");
        String endColumn = consoleScanner.next().toUpperCase();

        System.out.println("Enter Course Of Choices: ");

        for(Course aCourse: Course.getCourses()) {
            System.out.println("-> " + aCourse.getName());
        }

        String courseInput = consoleScanner.next().toUpperCase();

        Course course = SearchHelper.linearSearch(Course.getCourses(),
                c -> c.getName().equalsIgnoreCase(courseInput));

        consoleScanner.close();



        //get last chosen files
        File lastFileSaveDir = new File("lastFileDir.sav");
        File lastOutputFileSaveDir = new File("outputFileDir.sav");

        File lastFileDir = null;
        File lasOutputFileDir = null;

        try (Scanner scan = new Scanner(lastFileSaveDir);
            Scanner scan2 = new Scanner(lastOutputFileSaveDir)) {

            if(!lastFileSaveDir.exists()){ lastFileSaveDir.createNewFile(); }
            if(!lastOutputFileSaveDir.exists()){ lastOutputFileSaveDir.createNewFile(); }

            lastFileDir = new File(scan.next());
            lasOutputFileDir = new File(scan2.next());
        }
        catch (IOException ex) {
            System.out.println("Error while creating last files");
            System.exit(1);
        }

        //choose files
        File zybooksAssignmentsSummaryFile = FileHelper.getFileFromChooser(lastFileDir, "CSV");
        File outputFile = FileHelper.getFileFromChooser(lasOutputFileDir, null);

        //Parsing here
        ZybooksAssignmentsIngestor.parseGrades(course, endColumn, zybooksAssignmentsSummaryFile);

        NameValidator.validateNames(course);

        //save chosen files
        try ( PrintWriter out = new PrintWriter(lastFileSaveDir);
            PrintWriter out2 = new PrintWriter(lastOutputFileSaveDir)) {
            out.println(zybooksAssignmentsSummaryFile.getAbsolutePath());
            out2.println(outputFile.getAbsolutePath());
        }
        catch (IOException ex) {
            System.out.println("Error while saving last files");
            System.exit(1);
        }

        CourseGradesWriter.writeGradesFile(outputFile, course.getName(), usePercentage);
    }
}
