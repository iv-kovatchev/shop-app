package org.exercise.store;
import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreService.IStoreService;
import org.exercise.store.StoreService.StoreService;
import org.exercise.warehouse.IWarehouse;

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

    public void addGood(IWarehouse warehouse,  Category category) {
        this.storeService.addGood(warehouse, category);
    }

    public void addGoods(IWarehouse warehouse, Category category, int quantity) {
        this.storeService.addGoods(warehouse, category, quantity);
    }

    public void buildPayDesk() {
        this.storeService.buildPayDesk();
    }

    public IPayDesk getPayDeskById(int id) {
        return this.storeService.getPayDeskById(id);
    }

    public List<IGood> getFoodGoods() {
        return this.storeService.getFoodGoods();
    }

    public List<IGood> getNonFoodGoods() {
        return this.storeService.getNonFoodGoods();
    }

    public ICashier getCashierByName(String name) {
        return this.storeService.getCashierByName(name);
    }

    public ICashier getCashierById(int id) {
        return this.storeService.getCashierById(id);
    }

    public void hireCashier(ICashier cashier) {
        this.storeService.hireCashier(cashier);
    }

    public void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier) {
        this.storeService.addCashierToPayDesk(payDesk, cashier);
    }

    public void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier) {
        this.storeService.removeCashierFromPayDesk(payDesk, cashier);
    }

    public void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, int payDeskId) {
        this.storeService.sellGoods(foodQuantity, nonFoodQuantity, clientMoney, payDeskId);
    }
}
