package com.upgrade;

import com.upgrade.helpers.FileHelper;
import com.upgrade.helpers.operations.ConfigInjestor;
import com.upgrade.helpers.operations.ZybooksAssignmentSummaryInjestor;

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
                    printGradeSummary();
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        catch(InputMismatchException ex){
            System.out.println("Invalid input");
        }
    }

    private static void printGradeSummary() {
        File lastFileDir = new File("lastFileDir.sav");
        try {
            if(!lastFileDir.exists()){ lastFileDir.createNewFile(); }
        }
        catch (IOException ex) {
            //TODO- IOException
            System.out.println("Error while creating file");
            System.exit(1);
        }

        File zybooksAssignmentsSummaryFile = FileHelper.getFileFromChooser(lastFileDir, "CSV");

        ZybooksAssignmentSummaryInjestor.parseGrades(zybooksAssignmentsSummaryFile);

        try ( PrintWriter out = new PrintWriter(lastFileDir, "UTF-8")) {

        }
        catch (IOException ex) {
            //TODO- IOException
            System.out.println("Error while creating file");
        }


        //TODO grade summary
    }
}
