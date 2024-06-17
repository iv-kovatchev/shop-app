package org.exercise.store.StoreGoodsService;

import org.exercise.models.cashreceipt.CashReceipt;
import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;
import java.time.temporal.ChronoUnit;

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
        if (foodQuantity > this.foodGoods.size() || nonFoodQuantity > this.nonFoodGoods.size()) {
            throw new IndexOutOfBoundsException("There aren't enough goods!");
        } else {
            double total = 0.0;

            //NQMA NUJDA OT MINAVANE V LIST, SAMO SYS STREAM

            if (foodQuantity > 0) {
                total += this.foodGoods
                        .stream()
                        .limit(foodQuantity)
                        .peek(good -> {
                            if(isExpirySoon(good)) {
                                double reductionPrice = (good.getPrice() * this.reductionPricePercent) / 100;
                                good.setPrice(good.getPrice() - reductionPrice);
                            }
                        })
                        .mapToDouble(IGood::getPrice)
                        .sum();
            }

            if (nonFoodQuantity > 0) {
                total += this.nonFoodGoods
                        .stream()
                        .limit(nonFoodQuantity)
                        .peek(good -> {
                            if(isExpirySoon(good)) {
                                double reductionPrice = (good.getPrice() * this.reductionPricePercent) / 100;
                                good.setPrice(good.getPrice() - reductionPrice);
                            }
                        })
                        .mapToDouble(IGood::getPrice)
                        .sum();
            }

            if (total <= clientMoney) {
                System.out.printf("Well done!!! We just sold " +
                        (foodQuantity + nonFoodQuantity) +
                        " products and have total profit: %.2f$%n", total);

                //HERE WE WILL CREATE NEW CASH RECEIPT

                ICashReceipt cashReceipt =
                        new CashReceipt(payDesk.getCashier().getName(), LocalDateTime.now(), foodGoods, nonFoodGoods, total);
                System.out.println(cashReceipt.toString());


                this.nonFoodGoods.removeAll(this.nonFoodGoods.stream().limit(nonFoodQuantity).toList());
                this.foodGoods.removeAll(this.foodGoods.stream().limit(foodQuantity).toList());
            } else {
                throw new IllegalArgumentException("The client doesn't have enough money!");
            }
        }
    }

    public void addGood(IWarehouse warehouse, Category category) {
        try {
            IGood good = warehouse.getAllGoodsByCategory(category).getFirst();

            if (category == Category.FOOD) {
                foodGoods.add(good);
            } else {
                nonFoodGoods.add(good);
            }

            warehouse.removeGood(good);
            overpriceGood(good);

            System.out.println("The good was delivered!");
        }
        catch (NotEnoughGoodsByCategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGoods(IWarehouse warehouse, Category category, int quantity) {
        try {
            List<IGood> goods = warehouse.getNumberOfGoodsByCategory(category, quantity);

            for (IGood good : goods) {
                warehouse.removeGood(good);
                overpriceGood(good);
            }

            if (category == Category.FOOD) {
                foodGoods.addAll(goods);
            } else {
                nonFoodGoods.addAll(goods);
            }

            System.out.println("The goods were delivered!");
        } catch (NotEnoughGoodsByCategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    private void overpriceGood(IGood good) {
        double overprice =
                good.getPrice() * (good.getCategory().name().equals("FOOD") ?
                        (double) this.foodOverpricePercent / 100 :
                        (double) this.nonFoodOverpricePercent / 100);

        good.setPrice(good.getPrice() + overprice);
    }

    private boolean isExpirySoon(IGood good) {
        return ChronoUnit
                .DAYS
                .between(LocalDateTime.now(), good.getExpiryDate()) < this.daysBeforeExpiryDate;
    }
}
