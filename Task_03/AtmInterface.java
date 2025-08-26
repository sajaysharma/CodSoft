// package Task_03;

import java.util.Scanner;

// Class to represent a Bank Account
class BankAccount{
    private double balance;

    // Constructor
    public BankAccount(double balance){
        this.balance = balance;
    }

    // Method for Deposit
    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println("Succesfully Deposited :Rs. " + amount);
        } else {
            System.out.println("Invalid deposit Amount!");
        }
    }

    // Method for Withdrawal 
    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println("Succesfully Withdrawal :Rs. " + amount);
        }else if(amount > balance){
            System.out.println("Insufficient balance!");
        }else {
            System.out.println("Invalid Withdrawal Amount!");
        }
    }

    // Method for check Balance
    double getBalance(){
        return balance;
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display ATM Menu
void showMenu() {
        int option;
        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Available Total Balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println(" Exiting... Thank you for using our ATM!");
                    break;
                default:
                    System.out.println(" Invalid option! Please try again.");
            }
        } while (option != 4);
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Initial Balance $1000
        ATM atm = new ATM(userAccount);
        atm.showMenu();
    }
}
