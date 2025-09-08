package org.example;
public class Expense{
    private double amount;
    private String category;
    private String note;
    private String date;  // keep as String for now

    public Expense(double amount, String category, String note, String date) {
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " | " + category + " | " + amount + " | " + note;
    }
}
