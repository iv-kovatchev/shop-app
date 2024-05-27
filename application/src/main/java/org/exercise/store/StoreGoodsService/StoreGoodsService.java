package org.exercise.store.StoreGoodsService;

import org.exercise.cashier.Cashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class StoreGoodsService implements IStoreGoodsService {
    private List<IGood> goods;
    private int nonFoodOverpricePercent;
    private int foodOverpricePercent;
    private int reductionPricePercent;
    private int daysBeforeExpiryDate;

    public StoreGoodsService(int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.goods = new ArrayList<>();
        this.nonFoodOverpricePercent = nonFoodOverpricePercent;
        this.foodOverpricePercent = foodOverpricePercent;
        this.reductionPricePercent = reductionPricePercent;
        this.daysBeforeExpiryDate = daysBeforeExpiryDate;
    }

    public List<IGood> getGoods() {
        return goods;
    }

    public void addGood(Category category, IWarehouse warehouse) {
        List<IGood> goods = warehouse.getAllGoodsByCategory(category);

        if(!goods.isEmpty()) {
            IGood good = goods.getFirst();
            overpriceGood(good);

            this.goods.add(good);
            warehouse.getAllGoods().remove(good);

            System.out.println("The goods were delivered!");
        }
        else {
            throw new NoSuchElementException("There is not enough goods from this category in the warehouse!");
        }
    }

    private void overpriceGood(IGood good) {
        double overprice =
                good.getSalePrice() * (good.getCategory().name().equals("FOOD") ?
                        (double) this.foodOverpricePercent / 100 :
                        (double) this.nonFoodOverpricePercent / 100);

        good.setSalePrice(good.getSalePrice() + overprice);
    }
}
