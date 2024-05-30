package org.exercise.paydesk;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;

public class PayDesk implements IPayDesk {
    private static int idCounter = 1;
    private final int id;
    private ICashier cashier;
    private double profit;

    public PayDesk() {
        this.cashier = null;
        this.profit = 0;

        //Generate ID
        this.id = generateId();
    }

    public int getId() {
        return id;
    }

    public void setCashier(ICashier cashier) {
        this.cashier = cashier;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public ICashier getCashier() {
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
