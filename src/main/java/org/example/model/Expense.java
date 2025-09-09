package org.example.model;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private String note;
    private String date; // keep as String for simplicity (YYYY-MM-DD)

    public Expense(int id, double amount, String category, String note, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return id + " | " + amount + " | " + category + " | " + note + " | " + date;
    }
}
