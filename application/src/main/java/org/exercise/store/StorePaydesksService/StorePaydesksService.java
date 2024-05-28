package org.exercise.store.StorePaydesksService;

import org.exercise.cashier.Cashier;
import org.exercise.paydesk.PayDesk;
import org.exercise.store.StoreCashiersService.StoreCashiersService;

import java.util.HashSet;

public class StorePaydesksService {
    private final HashSet<PayDesk> payDesks;
    private StoreCashiersService storeCashiersService;

    public StorePaydesksService(StoreCashiersService storeCashiersService) {
        this.storeCashiersService = storeCashiersService;
        this.payDesks = new HashSet<>();
    }

    public void buildPayDesk(PayDesk payDesk) {
        if(!this.payDesks.contains(payDesk)) {
            this.payDesks.add(payDesk);
            System.out.println("You added pay desk successfully!");
        }
        else {
            System.out.println("You can't build the same pay desk!");
        }
    }

    public void addCashierToPayDesk(PayDesk payDesk, Cashier cashier) {
        if(this.payDesks.contains(payDesk)) {
            if(payDesk.getCashier() == null
                    && this.storeCashiersService.getCashiers().contains(cashier)) {
                payDesk.setCashier(cashier);
            }
            else {
                System.out.println("There is already a cashier in the pay desk!");
            }
        }
        else {
            System.out.println("The pay desk doesn't exist!");
        }
    }

    public void removeCashierFromPayDesk(PayDesk payDesk) {
        if(this.payDesks.contains(payDesk)) {
            payDesk.setCashier(null);
        }
        else {
            System.out.println("The pay desk doesn't exist!");
        }
    }

    public HashSet<PayDesk> getPayDesks() {
        return this.payDesks;
    }
}
