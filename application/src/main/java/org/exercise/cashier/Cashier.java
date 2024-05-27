package org.exercise.cashier;

public class Cashier {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private double monthSalary;

    public Cashier(int id, String name, double monthSalary) {
        this.id = generateId();
        this.name = name;
        this.monthSalary = monthSalary;
    }

    private synchronized static int generateId() {
        return idCounter++;
    }
}
