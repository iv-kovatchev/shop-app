package org.exercise.store.StorePaydesksService;

import org.exercise.paydesk.IPayDesk;

import java.util.HashSet;

public interface IStorePaydesksService {
    void buildPayDesk();

    HashSet<IPayDesk> getPayDesks();

    IPayDesk getPayDeskById(int id);
}
