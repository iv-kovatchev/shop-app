package org.exercise.store.StorePaydesksService;

import org.exercise.models.paydesk.IPayDesk;
import org.exercise.models.paydesk.PayDesk;

import java.util.HashSet;
import java.util.NoSuchElementException;

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
        IPayDesk payDesk = this.payDesks.stream().filter(pd -> pd.getId() == id).findFirst().orElse(null);

        if (payDesk == null) {
            throw new NoSuchElementException("There is no pay-desk with this id!");
        }

        return payDesk;
    }

    @Override
    public String toString() {
        return "StorePaydesksService{" +
                "payDesks=" + payDesks +
                '}';
    }
}
