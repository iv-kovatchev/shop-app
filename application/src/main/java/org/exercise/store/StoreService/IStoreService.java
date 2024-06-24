package org.exercise.store.StoreService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.util.HashSet;
import java.util.List;

public interface IStoreService {
    /**
     * Deliver new good by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void deliverGood(IWarehouse warehouse, Category category);

    /**
     * Deliver number of goods by category to the store from the warehouse if there is
     * enough amount of it.
     */
    void deliverGoods(IWarehouse warehouse, Category category, int quantity);

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
    void addCashierToPayDesk(int payDeskId, int cashierId);

    /**
     * Remove Cashier from PayDesk
     */
    void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier);

    /**
     * Sell Food and Non-Food Goods to client if there is enough quantity
     */
    void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, int payDeskId);

    /**
     * Show information about the store: total salaries, total delivery cost, total profit from sold goods
     */
    void storeInfo(String name);

    /**
     * Static deserialize of goods
     */
    void deserializeGoods();
}
