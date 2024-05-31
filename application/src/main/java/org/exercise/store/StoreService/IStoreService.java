package org.exercise.store.StoreService;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;

import java.util.HashSet;
import java.util.List;

public interface IStoreService {
    /**
     * Add new good by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void addGood(IWarehouse warehouse, Category category);

    /**
     * Add number of goods by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void addGoods(IWarehouse warehouse, Category category, int quantity);

    /**
     * Return all food goods in the store
     */
    List<IGood> getFoodGoods();

    /**
     * Return all non-food goods in the store
     */
    List<IGood> getNonFoodGoods();

    /**
     * Add new PayDesk in the store
     */
    void buildPayDesk();

    /**
     * Return all PayDesks in the store
     */
    HashSet<IPayDesk> getPayDesks();

    /**
     * Get PayDesk by id
     */
    IPayDesk getPayDeskById(int id);

    /**
     * Hire new Cashier in the store
     */
    void hireCashier(ICashier cashier);

    /**
     * Get Cashier by @id
     */
    ICashier getCashierById(int id);

    /**
     * Get Cashier by @name
     */
    ICashier getCashierByName(String name);

    /**
     * Add Cashier to PayDesk if it's free
     */
    void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier);

    /**
     * Remove Cashier from PayDesk
     */
    void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier);

    /**
     * Sell Food and Non-Food Goods to client if there is enough quantity
     */
    void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, int payDeskId);
}
