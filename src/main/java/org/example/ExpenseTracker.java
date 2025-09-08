package org.example;
import java.util.*;
import java.io.*;

public class ExpenseTracker{
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Save to File");
            System.out.println("4. Load from File");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    addExpense(sc);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    saveToFile();
                    break;
                case 4:
                    loadFromFile();
                    break;
                case 5:
                    System.out.println("Thank you for using Smart Expense Tracker");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while(choice != 5);
    }

    private static void addExpense(Scanner sc) {
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter note: ");
        String note = sc.nextLine();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        expenses.add(new Expense(amount, category, note, date));
        System.out.println("Expense added!");
    }

    private static void viewExpenses() {
        if(expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        System.out.println("--- Expenses ---");
        for(Expense e : expenses) {
            System.out.println(e);
        }
    }

    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("expenses.txt"))) {
            for(Expense e : expenses) {
                pw.println(e);
            }
            System.out.println("Saved to file!");
        } catch(Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File("expenses.txt"))) {
            expenses.clear();
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            System.out.println("Loaded from file!");
        } catch(Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}
