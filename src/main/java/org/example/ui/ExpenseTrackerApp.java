package org.example.ui;

import org.example.service.ExpenseService;
import org.example.model.Expense;

import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {
    private static final ExpenseService service = new ExpenseService();

    public static void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter category: ");
                String category = sc.nextLine();
                System.out.print("Enter note: ");
                String note = sc.nextLine();
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                service.addExpense(amount, category, note, date);

            } else if (choice == 2) {
                List<Expense> expenses = service.getAllExpenses();
                if (expenses.isEmpty()) System.out.println("⚠️ No expenses found.");
                else for (Expense e : expenses) System.out.println(e);

            } else if (choice == 3) {
                System.out.print("Enter ID to update: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Enter new amount: ");
                double amt = sc.nextDouble(); sc.nextLine();
                System.out.print("Enter new category: ");
                String cat = sc.nextLine();
                System.out.print("Enter new note: ");
                String n = sc.nextLine();
                System.out.print("Enter new date (YYYY-MM-DD): ");
                String d = sc.nextLine();
                service.updateExpense(id, amt, cat, n, d);

            } else if (choice == 4) {
                System.out.print("Enter ID to delete: ");
                int id = sc.nextInt(); sc.nextLine();
                service.deleteExpense(id);

            } else if (choice == 5) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
