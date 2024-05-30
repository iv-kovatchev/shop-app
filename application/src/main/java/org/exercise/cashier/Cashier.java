package org.exercise.cashier;

public class Cashier implements ICashier {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private double monthSalary;
    private boolean isHired;

    public Cashier(String name, double monthSalary) {
        this.id = idCounter++;
        this.name = name;
        this.monthSalary = monthSalary;
        this.isHired = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isHired() {
        return isHired;
    }

    public void setHired(boolean hired) {
        isHired = hired;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthSalary=" + monthSalary +
                '}';
    }
}
