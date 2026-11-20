package com.mycompany.assignment2_route;

import java.util.Scanner;

public class Assignment2_Route {

    static double balance = 2500.75;
    static int count = 0; 

    static boolean authenticate(Scanner scanner, String correctPin, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Enter your 4-digit PIN: ");
            String pin = scanner.nextLine().trim();

            if (pin.equals(correctPin)) {
                return true;
            }

            int remaining = maxAttempts - attempt;
            if (remaining > 0) {
                System.out.println("Incorrect PIN. You have " + remaining + " attempt(s) left.");
            }
        }
        return false;
    }

    static void showMenu() {
        System.out.println();
        System.out.println("========= ATM =========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show Account Status");
        System.out.println("5. Exit");
        System.out.println("=======================");
        System.out.print("Choose an option: ");
    }

  
  static double readAmount(Scanner scanner, String prompt) {
    System.out.print(prompt);
    if (scanner.hasNextDouble()) {
        double value = scanner.nextDouble();
        scanner.nextLine(); 
        return value;
    } else {
        scanner.nextLine();
        return 0;
    }
}   

    static void checkBalance() {
        System.out.printf("Current balance: %.2f%n", balance);
    }

    static void deposit(Scanner scanner) {
        double amount = readAmount(scanner, "Enter deposit amount: ");
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        balance += amount;
        count++;
        System.out.printf("Deposit successful. New balance: %.2f%n", balance);
    }

    static void withdraw(Scanner scanner) {
        double amount = readAmount(scanner, "Enter withdrawal amount: ");
        if(amount == 0){
        System.out.println("Transaction cancelled.");
        }
        else if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            count++;
            System.out.printf("Withdrawal successful. New balance: %.2f%n", balance);
             if (Math.abs(balance) < 0.005) {
                System.out.println("Warning: Your account is empty.");
            }
        }
    }

    static void showAccountStatus() {
        if (balance >= 5000) {
            System.out.println("VIP Customer");
        } else if (balance >= 1000) {
            System.out.println("Regular Customer");
        } else {
            System.out.println("Low Balance");
        }
    }

    public static void main(String[] args) {
        
         Scanner scanner = new Scanner(System.in);
        
        if (!authenticate(scanner, "1234", 3)) {
            System.out.println("Your account has been locked.");
            return;
        }

        System.out.println("Login successful.");

        boolean running = true;
        while (running) {
            showMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    deposit(scanner);
                    break;
                case "3":
                    withdraw(scanner);
                    break;
                case "4":
                    showAccountStatus();
                    break;
                case "5":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.out.println("Total successful transactions: " + count);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }

        scanner.close();
    }
}