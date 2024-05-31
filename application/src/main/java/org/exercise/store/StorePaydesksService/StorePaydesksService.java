package org.exercise.store.StorePaydesksService;

import org.exercise.paydesk.IPayDesk;
import org.exercise.paydesk.PayDesk;

import java.util.HashSet;

public class StorePaydesksService implements IStorePaydesksService {
    private final HashSet<IPayDesk> payDesks;

    public StorePaydesksService() {
        this.payDesks = new HashSet<>();
    }

    public void buildPayDesk() {
        this.payDesks.add(new PayDesk());
    }

    public HashSet<IPayDesk> getPayDesks() {
        return this.payDesks;
    }

    public IPayDesk getPayDeskById(int id) {
        for(IPayDesk payDesk : this.payDesks) {
            if(payDesk.getId() == id) {
                return payDesk;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "StorePaydesksService{" +
                "payDesks=" + payDesks +
                '}';
    }
}
