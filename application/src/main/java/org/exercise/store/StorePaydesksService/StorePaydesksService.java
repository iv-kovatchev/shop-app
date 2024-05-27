package org.exercise.store.StorePaydesksService;

import org.exercise.paydesk.PayDesk;

import java.util.HashSet;

public class StorePaydesksService {
    private final HashSet<PayDesk> payDesks;

    public StorePaydesksService() {
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

    public HashSet<PayDesk> getPayDesks() {
        return this.payDesks;
    }
}
