package org.exercise.paydesk;

import org.exercise.cashier.ICashier;

public interface IPayDesk {
    void setCashier(ICashier cashier);

    int getId();

    void setProfit(double profit);

    ICashier getCashier();

    double getProfit();
}
