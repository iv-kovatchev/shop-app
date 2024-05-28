package org.exercise.store.StoreCashiersService;

import org.exercise.cashier.Cashier;

import java.util.HashSet;

public class StoreCashiersService {
    private final HashSet<Cashier> cashiers;

    public StoreCashiersService() {
        this.cashiers = new HashSet<>();
    }

    public void hireCashier(Cashier cashier) {
        if(!this.cashiers.contains(cashier) && !cashier.isHired()) {
            this.cashiers.add(cashier);
            System.out.println("This cashier " + cashier.getName() + " was hired!");
        }
        else {
            System.out.println("This cashier is already hired!");
        }
    }

    public HashSet<Cashier> getCashiers() {
        return cashiers;
    }
}
