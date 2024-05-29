package org.exercise.store.StorePaydesksService;

import org.exercise.paydesk.PayDesk;

import java.util.HashSet;

public class StorePaydesksService implements IStorePaydesksService {
    private final HashSet<PayDesk> payDesks;

    public StorePaydesksService() {
        this.payDesks = new HashSet<>();
    }

    public void buildPayDesk() {
        this.payDesks.add(new PayDesk());
    }

    public HashSet<PayDesk> getPayDesks() {
        return this.payDesks;
    }

    @Override
    public String toString() {
        return "StorePaydesksService{" +
                "payDesks=" + payDesks +
                '}';
    }
}
