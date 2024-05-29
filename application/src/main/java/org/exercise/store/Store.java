package org.exercise.store;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
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

    public void addGood(Category category, IWarehouse warehouse) {
        this.storeService.addGood(category, warehouse);
    }

    public void addGoods(Category category, IWarehouse warehouse, int quantity) {
        this.storeService.addGoods(category, warehouse, quantity);
    }

    public void buildPayDesk() {
        this.storeService.buildPayDesk();
    }

    public List<IGood> getGoods() {
        return this.storeService.getGoods();
    }
}
