package org.exercise.store.StoreGoodsService;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.warehouse.IWarehouse;

import java.util.*;

public class StoreGoodsService implements IStoreGoodsService {
    private List<IGood> foodGoods;
    private List<IGood> nonFoodGoods;
    private int nonFoodOverpricePercent;
    private int foodOverpricePercent;
    private int reductionPricePercent;
    private int daysBeforeExpiryDate;

    public StoreGoodsService(int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.foodGoods = new ArrayList<>();
        this.nonFoodGoods = new ArrayList<>();
        this.nonFoodOverpricePercent = nonFoodOverpricePercent;
        this.foodOverpricePercent = foodOverpricePercent;
        this.reductionPricePercent = reductionPricePercent;
        this.daysBeforeExpiryDate = daysBeforeExpiryDate;
    }

    public List<IGood> getFoodGoods() {
        return this.foodGoods;
    }

    public List<IGood> getNonFoodGoods() {
        return this.nonFoodGoods;
    }

    public void addGood(IWarehouse warehouse, Category category) {
        IGood good = warehouse.getAllGoodsByCategory(category).getFirst();

        if(category == Category.FOOD) {
            foodGoods.add(good);
        }
        else {
            nonFoodGoods.add(good);
        }

        warehouse.removeGood(good);
        overpriceGood(good);

        System.out.println("The good was delivered!");
    }

    public void addGoods(IWarehouse warehouse, Category category, int quantity) {
        List<IGood> goods = warehouse.getNumberOfGoodsByCategory(category, quantity);

        for (IGood good : goods) {
            warehouse.removeGood(good);
            overpriceGood(good);
        }

        if(category == Category.FOOD) {
            foodGoods.addAll(goods);
        }
        else {
            nonFoodGoods.addAll(goods);
        }

        System.out.println("The goods were delivered!");
    }

    private void overpriceGood(IGood good) {
        double overprice =
                good.getSalePrice() * (good.getCategory().name().equals("FOOD") ?
                        (double) this.foodOverpricePercent / 100 :
                        (double) this.nonFoodOverpricePercent / 100);

        good.setSalePrice(good.getSalePrice() + overprice);
    }
}
