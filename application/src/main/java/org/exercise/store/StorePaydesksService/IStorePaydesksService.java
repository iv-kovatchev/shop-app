package org.exercise.store.StorePaydesksService;

import org.exercise.models.paydesk.IPayDesk;

import java.util.HashSet;

public interface IStorePaydesksService {
    void buildPayDesk();

    HashSet<IPayDesk> getPayDesks();

    IPayDesk getPayDeskById(int id);
}
