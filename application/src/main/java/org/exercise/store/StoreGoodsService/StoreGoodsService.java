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
        return this.goods;
    }

    public void addGood(Category category, IWarehouse warehouse) {
        IGood good = warehouse.getAllGoodsByCategory(category).getFirst();
        overpriceGood(good);

        this.goods.add(good);
        warehouse.removeGood(good);

        System.out.println("The good was delivered!");
    }

    public void addGoods(Category category, IWarehouse warehouse, int quantity) {
        List<IGood> goods = warehouse.getNumberOfGoodsByCategory(category, quantity);

        for (IGood good : goods) {
            overpriceGood(good);

            this.goods.add(good);
            warehouse.removeGood(good);
        }

        System.out.println("The goods was delivered!");
    }

    private void overpriceGood(IGood good) {
        double overprice =
                good.getSalePrice() * (good.getCategory().name().equals("FOOD") ?
                        (double) this.foodOverpricePercent / 100 :
                        (double) this.nonFoodOverpricePercent / 100);

        good.setSalePrice(good.getSalePrice() + overprice);
    }
}
