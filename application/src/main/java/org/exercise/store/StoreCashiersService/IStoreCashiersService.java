package org.exercise.store.StoreCashiersService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.paydesk.IPayDesk;

import java.util.HashSet;

public interface IStoreCashiersService {
    void hireCashier(ICashier cashier);

    ICashier getCashierById(int id);

    ICashier getCashierByName(String name);

    HashSet<ICashier> getCashiers();

    String toString();

    void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier);

    void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier);
}
