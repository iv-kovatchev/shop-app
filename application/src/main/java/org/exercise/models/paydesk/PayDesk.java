package org.exercise.models.paydesk;

import org.exercise.models.cashier.ICashier;

public class PayDesk implements IPayDesk {
    private static int idCounter = 1;
    private final int id;
    private ICashier cashier;

    public PayDesk() {
        this.cashier = null;

        //Generate ID
        this.id = generateId();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setCashier(ICashier cashier) {
        this.cashier = cashier;
    }

    @Override
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
