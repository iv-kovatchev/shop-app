package org.exercise.models.paydesk;

import org.exercise.models.cashier.ICashier;

public interface IPayDesk {
    void setCashier(ICashier cashier);

    int getId();

    ICashier getCashier();
}
