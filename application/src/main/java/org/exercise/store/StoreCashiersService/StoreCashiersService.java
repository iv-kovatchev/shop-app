package org.exercise.store.StoreCashiersService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.paydesk.IPayDesk;

import java.util.HashSet;

public class StoreCashiersService implements IStoreCashiersService {
    private final HashSet<ICashier> cashiers;

    public StoreCashiersService() {
        this.cashiers = new HashSet<>();
    }

    @Override
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

    @Override
    public ICashier getCashierById(int id) {
        return this.cashiers
                .stream().filter(c -> c.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public ICashier getCashierByName(String name) {
        return this.cashiers
                .stream().filter(c -> c.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public HashSet<ICashier> getCashiers() {
        return cashiers;
    }


    public void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier) {
        if(this.cashiers.contains(cashier)) {
            if(payDesk.getCashier() == null) {
                payDesk.setCashier(cashier);
                System.out.println("Cashier " + cashier.getName() + " was added to the pay desk!");
            }
            else {
                System.out.println("There is already cashier on this pay desk!");
            }
        }
        else {
            System.out.println("The cashier with that id isn't working in this store!");
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
