// package Task_02;

import java.util.Scanner;

public class StudentGradeCalculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of Subject: ");
        int subject = scanner.nextInt();

        int mark[] =new int[subject];
        int total = 0;
        for (int i = 0; i < subject; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            mark[i] = scanner.nextInt();
            total = total + mark[i];
        }
        
        double average = total/subject;

        String grade;
        if (average >= 90) {
            grade = "A+";
        } else if(average >= 80){
            grade = "A";
        }else if(average >= 70){
            grade = "B";
        }else if(average >= 60){
            grade = "C";
        }else if(average >= 50){
            grade = "D";
        }else {
            grade = "F (Fail)";
        }

        // Display results
        System.out.println("\n----- Result -----");
        System.out.println("Total Marks = " + total);
        System.out.println("Average Percentage = " + average + "%");
        System.out.println("Grade = " + grade);

        scanner.close();
    }
}
