package org.exercise.store.StoreGoodsService;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;

import java.util.List;

public interface IStoreGoodsService {
    List<IGood> getFoodGoods();

    List<IGood> getNonFoodGoods();

    void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, IPayDesk payDesk);

    void addGood(IWarehouse warehouse, Category category);

    void addGoods(IWarehouse warehouse, Category category, int quantity);
}
