package org.exercise.store;
import org.exercise.models.cashier.ICashier;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreService.IStoreService;
import org.exercise.store.StoreService.StoreService;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.util.*;

public class Store {
    private String name;
    private final IStoreService storeService;

    public Store(String name, int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.name = name;
        this.storeService = new StoreService(nonFoodOverpricePercent, foodOverpricePercent, reductionPricePercent, daysBeforeExpiryDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Deliver new good by category to the store from the warehouse if there is
     * enough amount of it.
     */
    public void deliverGood(IWarehouse warehouse,  Category category) {
        this.storeService.deliverGood(warehouse, category);
    }

    /**
     * Deliver number of goods by category to the store from the warehouse if there is
     * enough amount of it.
     */
    public void deliverGoods(IWarehouse warehouse, Category category, int quantity) {
        this.storeService.deliverGoods(warehouse, category, quantity);
    }

    /**
     * Add new PayDesk in the store
     */
    public void buildPayDesk() {
        this.storeService.buildPayDesk();
    }

    /**
     * Get PayDesk by id
     */
    public IPayDesk getPayDeskById(int id) {
        return this.storeService.getPayDeskById(id);
    }

    /**
     * Return all food goods in the store
     */
    public List<IGood> getFoodGoods() {
        return this.storeService.getFoodGoods();
    }

    /**
     * Return all non-food goods in the store
     */
    public List<IGood> getNonFoodGoods() {
        return this.storeService.getNonFoodGoods();
    }

    /**
     * Hire new Cashier in the store
     */
    public void hireCashier(ICashier cashier) {
        this.storeService.hireCashier(cashier);
    }

    /**
     * Get Cashier by @name
     */
    public ICashier getCashierByName(String name) {
        return this.storeService.getCashierByName(name);
    }

    /**
     * Get Cashier by @id
     */
    public ICashier getCashierById(int id) {
        return this.storeService.getCashierById(id);
    }

    /**
     * Add Cashier to PayDesk if it's free
     */
    public void addCashierToPayDesk(int payDeskId, int cashierId) {
        this.storeService.addCashierToPayDesk(payDeskId, cashierId);
    }

    /**
     * Remove Cashier from PayDesk
     */
    public void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier) {
        this.storeService.removeCashierFromPayDesk(payDesk, cashier);
    }

    /**
     * Sell Food and Non-Food Goods to client if there is enough quantity
     */
    public void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, int payDeskId) {
        this.storeService.sellGoods(foodQuantity, nonFoodQuantity, clientMoney, payDeskId);
    }

    /**
     * Show information about the store: total salaries, total delivery cost, total profit from sold goods
     */
    public void storeInfo() {
        this.storeService.storeInfo(this.name);
    }

    /**
     * Static deserialize of goods
     */
    public void deserializeGoods() {
        this.storeService.deserializeGoods();
    }
}
