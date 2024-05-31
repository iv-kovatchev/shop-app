package org.exercise.store.StoreGoodsService;

import org.exercise.cashreceipt.CashReceipt;
import org.exercise.cashreceipt.ICashReceipt;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;

import java.util.*;
import java.time.LocalDateTime;

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

    public void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, IPayDesk payDesk) {
        if(foodQuantity > this.foodGoods.size() || nonFoodQuantity > this.nonFoodGoods.size()) {
            throw new IndexOutOfBoundsException("There aren't enough goods!");
        }
        else {
            double total = 0.0;

            List<IGood> nonFoodGoods = this.nonFoodGoods.stream().limit(nonFoodQuantity).toList();
            List<IGood> foodGoods = this.foodGoods.stream().limit(foodQuantity).toList();

            if(foodQuantity > 0) {
                for(IGood good : foodGoods) {
                    total += good.getSalePrice();
                }
            }

            if(nonFoodQuantity > 0) {
                for(IGood good : nonFoodGoods) {
                    total += good.getSalePrice();
                }
            }

            if(total <= clientMoney) {
                System.out.printf("Well done!!! We just sold " +
                        (foodQuantity+nonFoodQuantity) +
                        " products and have total profit: %.2f$%n", total);

                //HERE WE WILL CREATE NEW CASH RECEIPT

                ICashReceipt cashReceipt =
                        new CashReceipt(payDesk.getCashier().getName(), LocalDateTime.now(), foodGoods, nonFoodGoods, total);
                System.out.println(cashReceipt.toString());
            }
            else {
                throw new IllegalArgumentException("The client doesn't have enough money!");
            }
        }
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
