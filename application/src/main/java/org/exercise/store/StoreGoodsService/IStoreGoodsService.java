package org.exercise.store.StoreGoodsService;

import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;

import java.util.List;

public interface IStoreGoodsService {
    List<IGood> getFoodGoods();

    List<IGood> getNonFoodGoods();

    ICashReceipt sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, IPayDesk payDesk);

    void addGood(IWarehouse warehouse, Category category);

    void addGoods(IWarehouse warehouse, Category category, int quantity);
}
