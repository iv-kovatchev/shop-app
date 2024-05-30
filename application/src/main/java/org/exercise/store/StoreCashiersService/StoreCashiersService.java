package org.exercise.store.StoreCashiersService;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;
import org.exercise.paydesk.IPayDesk;

import java.util.HashSet;

public class StoreCashiersService implements IStoreCashiersService {
    private final HashSet<ICashier> cashiers;

    public StoreCashiersService() {
        this.cashiers = new HashSet<>();
    }

    public void hireCashier(ICashier cashier) {
        if(!cashier.isHired()) {
            this.cashiers.add(cashier);
            cashier.setHired(true);
            System.out.println("This cashier " + cashier.getName() + " was hired!");
        }
        else {
            System.out.println("This cashier is already hired!");
        }
    }

    public ICashier getCashierById(int id) {
        for(ICashier cashier : this.cashiers) {
            if(cashier.getId() == id) {
                return cashier;
            }
        }

        return null;
    }

    public ICashier getCashierByName(String name) {
        for(ICashier cashier : this.cashiers) {
            if(cashier.getName().equals(name)) {
                return cashier;
            }
        }

        return null;
    }

    public HashSet<ICashier> getCashiers() {
        return cashiers;
    }


    public void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier) {
        if(this.cashiers.contains(cashier)) {
            if(payDesk.getCashier() == null) {
                payDesk.setCashier(cashier);
            }
            else {
                System.out.println("There is already cashier on this pay desk!");
            }
        }
        else {
            System.out.println("The cashier isn't working in this store!");
        }
    }

    public void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier) {
        if(this.cashiers.contains(cashier)) {
            if(payDesk.getCashier() != null) {
                payDesk.setCashier(null);
            }
            else {
                System.out.println("There isn't any cashier on this pay desk!");
            }
        }
        else {
            System.out.println("The cashier isn't working in this store!");
        }
    }

    @Override
    public String toString() {
        return "StoreCashiersService{" +
                "cashiers=" + cashiers +
                '}';
    }
}
