package org.exercise.store;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.warehouse.IWarehouse;

import java.util.*;

public class Store {
    private String name;
    private final IStoreGoodsService storeGoodsService;

    public Store(String name, IStoreGoodsService storeGoodsService) {
        this.name = name;
        this.storeGoodsService = storeGoodsService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGood(Category category, IWarehouse warehouse) {
        try {
            this.storeGoodsService.addGood(category, warehouse);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<IGood> getGoods() {
        return this.storeGoodsService.getGoods();
    }
}
