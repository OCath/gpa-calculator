package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import utils.Table;
import models.Grade;
import models.GradePointAverage;
import models.UnitObtained;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Enter the number of courses you offer: ");
        int numberOfCourses = getTotalCourse();

        String[][] courseProfile = new String[numberOfCourses][3];
        for (int i = 0; i < numberOfCourses; i++) {
            String[] newProfile = new String[1];
            newProfile = getCourseProfile(i + 1);
            courseProfile[i] = newProfile;
        }
        
        String[][] gradedCourseProfile = Grade.getGrade(courseProfile);
        String[][] gradedUnitCourseProfile = UnitObtained.getUnitObtained(gradedCourseProfile);
        double gradePointAverage = GradePointAverage.getGradePointAverage(gradedUnitCourseProfile);

        Table.printTable(gradedUnitCourseProfile);

        String GPA = String.format("%.2f", gradePointAverage);
        System.out.printf("\n\nYour GPA is = %s to 2 decimal places.\n", GPA);

        scanner.close();
    }

    private static int getTotalCourse() {
        int numberOfCourses = 0;
        try {
            numberOfCourses = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Please insert a number not letters");
            scanner.nextLine();
            numberOfCourses = getTotalCourse();
        }
        return numberOfCourses;
    }
    
    private static String[] getCourseProfile(int index) {
        String[] courseProfile = new String[3];
        String courseName = "";
        int courseUnit = 0;
        int score = 0;

        //Get course and score details
        try {

            System.out.println("Enter the Course code " + index + ": ");
            if (scanner.hasNextLine()) {
                courseName = scanner.nextLine();
            }

            System.out.println("Enter the number of units for the course: ");
            while (!scanner.hasNextInt()) {
                System.err.println("Kindly input a number not alphabet or symbol.");
                scanner.nextLine();
            }
            courseUnit = scanner.nextInt();
            scanner.nextLine();
            while (courseUnit <= 0) {
                System.err.println("Kindly input a course unit greater than zero");
                courseUnit = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Enter the score obtained: ");
            while (!scanner.hasNextInt()) {
                System.err.println("Kindly input a valid score");
                scanner.nextLine();
            }
            score = scanner.nextInt();
            scanner.nextLine();
            while (score < 0 || score > 100) {
                System.err.println("Kindly input a score between 0 and 100");
                score = scanner.nextInt();
                scanner.nextLine();
            }

        
            courseProfile[0] = courseName;
            courseProfile[1] = String.valueOf(courseUnit);
            courseProfile[2] = String.valueOf(score);
        } catch (InputMismatchException e) {
            System.err.println("Kindly input the correct details");
            getCourseProfile(index);
        }

        return courseProfile;
    }
}