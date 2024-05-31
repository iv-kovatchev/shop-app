package org.exercise.paydesk;

import org.exercise.cashier.ICashier;

public interface IPayDesk {
    void setCashier(ICashier cashier);

    int getId();

    ICashier getCashier();
}
