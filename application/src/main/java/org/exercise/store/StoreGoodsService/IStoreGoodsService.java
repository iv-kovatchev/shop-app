package org.exercise.store.StoreGoodsService;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.warehouse.IWarehouse;

import java.util.List;

public interface IStoreGoodsService {
    List<IGood> getGoods();
    void addGood(Category category, IWarehouse warehouse);
}
