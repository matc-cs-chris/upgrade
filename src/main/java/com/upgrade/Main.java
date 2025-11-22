package com.upgrade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: GUI? Maybe?
//        Application.launch(HelloApplication.class, args);

        Scanner scan = new Scanner(System.in);

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

        //TODO grade summary
    }
}
