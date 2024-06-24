package org.exercise.store.StoreGoodsService;

import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughGoodsException;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughMoneyException;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.util.List;

public interface IStoreGoodsService {
    List<IGood> getFoodGoods();

    List<IGood> getNonFoodGoods();

    double getTotalDeliveryCost();

    double getTotalProfitBySoldGoods();

    ICashReceipt sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, IPayDesk payDesk)
            throws NotEnoughGoodsException, NotEnoughMoneyException;

    void addGood(IWarehouse warehouse, Category category) throws NotEnoughGoodsByCategoryException;

    void addGoods(IWarehouse warehouse, Category category, int quantity);

    IGood deserializeGood(int goodId, SerializeType type);
}
