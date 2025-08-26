// package Task_01;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("---- Welcome to the Number Guessing Game! ----");
        
        
        while (playAgain) {
            
            int randomNumber = rand.nextInt(100) + 1;
            int attempts = 5;
            boolean guessedNumber = false;

            System.out.println("\nI have generated a number between 1 to 100. ");
            System.out.println("You have " + attempts + " attempts to guess it. Good luck!");

           
            while (attempts > 0) {
                System.out.print("Enter your guess: ");              
                int number = scanner.nextInt();

                
                if (number == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number: " + randomNumber);
                    guessedNumber = true;
                    roundsWon++; // Increment score

                } else if (number < randomNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                attempts--;
                if (attempts > 0) {
                    System.out.println("Attempts left: " + attempts);
                }
            }
            
            if (!guessedNumber) {
                System.out.println("\nSorry, you've run out of attempts. The correct number was " + randomNumber + ".");
            }
            
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        
        System.out.println("\n--- Game Over ---");
        System.out.println("Thank you for playing! Your final score is: " + roundsWon + " rounds won.");

        
        scanner.close();
        
    }
}




