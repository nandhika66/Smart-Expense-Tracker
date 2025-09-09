package org.example.service;

import org.example.db.DatabaseConnection;
import org.example.model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    // CREATE
    public void addExpense(double amount, String category, String note, String date) {
        String sql = "INSERT INTO expenses (amount, category, note, date) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setString(2, category);
            ps.setString(3, note);

            // If your DB column is DATE, convert string "YYYY-MM-DD" to java.sql.Date:
            ps.setDate(4, java.sql.Date.valueOf(date)); // safer if date column is DATE

            ps.executeUpdate();
            System.out.println("✅ Expense added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("note"),
                        rs.getDate("date").toString() // convert Date to String YYYY-MM-DD
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateExpense(int id, double newAmount, String newCategory, String newNote, String newDate) {
        String sql = "UPDATE expenses SET amount=?, category=?, note=?, date=? WHERE id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newAmount);
            ps.setString(2, newCategory);
            ps.setString(3, newNote);
            ps.setDate(4, java.sql.Date.valueOf(newDate));
            ps.setInt(5, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Expense updated!" : "⚠️ Expense not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "🗑️ Expense deleted!" : "⚠️ Expense not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
