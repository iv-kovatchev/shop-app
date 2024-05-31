package org.exercise.paydesk;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;

public class PayDesk implements IPayDesk {
    private static int idCounter = 1;
    private final int id;
    private ICashier cashier;

    public PayDesk() {
        this.cashier = null;

        //Generate ID
        this.id = generateId();
    }

    public int getId() {
        return id;
    }

    public void setCashier(ICashier cashier) {
        this.cashier = cashier;
    }

    public ICashier getCashier() {
        return cashier;
    }

    @Override
    public String toString() {
        return "PayDesk{" +
                "id=" + id +
                ", cashier=" + cashier +
                '}';
    }

    private synchronized static int generateId() {
        return idCounter++;
    }
}
