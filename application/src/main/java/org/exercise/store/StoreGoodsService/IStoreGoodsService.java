package org.exercise.store.StoreGoodsService;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.warehouse.IWarehouse;

import java.util.List;

public interface IStoreGoodsService {
    List<IGood> getFoodGoods();

    List<IGood> getNonFoodGoods();

    void addGood(IWarehouse warehouse, Category category);

    void addGoods(IWarehouse warehouse, Category category, int quantity);
}
