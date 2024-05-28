package org.exercise.paydesk;

import org.exercise.cashier.Cashier;

public class PayDesk {
    private static int idCounter = 1;
    private final int id;
    private Cashier cashier;
    private double profit;

    public PayDesk() {
        this.cashier = null;
        this.profit = 0;

        //Generate ID
        this.id = generateId();
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "PayDesk{" +
                "id=" + id +
                ", cashier=" + cashier +
                ", profit=" + profit +
                '}';
    }

    private synchronized static int generateId() {
        return idCounter++;
    }
}
