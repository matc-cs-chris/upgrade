package com.upgrade;

import com.upgrade.helpers.ExcelHelper;
import com.upgrade.helpers.FileHelper;
import com.upgrade.helpers.SearchHelper;
import com.upgrade.helpers.operations.ConfigInjestor;
import com.upgrade.helpers.operations.ZybooksAssignmentSummaryInjestor;
import com.upgrade.model.general.Course;

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

        ConfigInjestor.parseCourses();

        System.out.println("Enter operation (or 'quit'): ");
        System.out.println("1 - Print Grade Summary");

        try{
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    printGradeSummary(scan);
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        catch(InputMismatchException ex){
            System.out.println("Invalid input");
        }


    }

    private static void printGradeSummary(Scanner consoleScanner) {
        System.out.println("Enter End Column Letter Code: ");
        String endColumn = consoleScanner.next().toUpperCase();

        System.out.println("Enter Course Of Choices: ");

        for(Course aCourse: Course.getCourses()) {
            System.out.println("-> " + aCourse.getName());
        }

        Course course = SearchHelper.linearSearch(Course.getCourses(),
                c -> c.getName().toUpperCase().equals(consoleScanner.next().toUpperCase()));

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
        ZybooksAssignmentSummaryInjestor.parseGrades(course, endColumn,
                zybooksAssignmentsSummaryFile, outputFile);

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
    }
}
