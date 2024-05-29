package org.exercise.store.StoreService;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.warehouse.IWarehouse;

import java.util.HashSet;
import java.util.List;

public interface IStoreService {
    /**
     * Add new good by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void addGood(Category category, IWarehouse warehouse);

    /**
     * Add number of goods by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void addGoods(Category category, IWarehouse warehouse, int quantity);

    /**
     * Return all goods in the store
     */
    List<IGood> getGoods();

    /**
     * Add new PayDesk in the store
     */
    void buildPayDesk();

    /**
     * Return all PayDesks in the store
     */
    HashSet<PayDesk> getPayDesks();
}
