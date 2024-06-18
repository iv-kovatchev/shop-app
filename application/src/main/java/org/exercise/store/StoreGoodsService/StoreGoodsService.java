package org.exercise.store.StoreGoodsService;

import org.exercise.models.cashreceipt.CashReceipt;
import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughGoodsException;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughMoneyException;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.time.temporal.ChronoUnit;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreGoodsService implements IStoreGoodsService {
    private List<IGood> foodGoods;
    private List<IGood> nonFoodGoods;
    private int nonFoodOverpricePercent;
    private int foodOverpricePercent;
    private int reductionPricePercent;
    private int daysBeforeExpiryDate;

    /**
     * Another strategy which in my opinion is smarter
     * We use EnumMap and add all goods by their category
     */
    private EnumMap<Category, IGood> storeGoods;

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

    public ICashReceipt sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, IPayDesk payDesk) {
        removeExpiredGoods(Category.FOOD);
        removeExpiredGoods(Category.NON_FOOD);

        if (foodQuantity > this.foodGoods.size() || nonFoodQuantity > this.nonFoodGoods.size()) {
            throw new NotEnoughGoodsException("There aren't enough goods!");
        } else {
            double total = getTotalPrice(foodQuantity, nonFoodQuantity);

            if (total <= clientMoney) {
                this.nonFoodGoods.removeAll(this.nonFoodGoods.stream().limit(nonFoodQuantity).toList());
                this.foodGoods.removeAll(this.foodGoods.stream().limit(foodQuantity).toList());

                return new CashReceipt(payDesk.getCashier().getName(), LocalDateTime.now(), foodGoods, nonFoodGoods, total);
            } else {
                throw new NotEnoughMoneyException("The client doesn't have enough money!");
            }
        }
    }

    /**
     * Return the total price of the order
     */
    private double getTotalPrice(int foodQuantity, int nonFoodQuantity) {
        double total = 0.0;
        if (foodQuantity > 0) {
            total += getTotalPriceByCategory(Category.FOOD, foodQuantity);
        }
        if (nonFoodQuantity > 0) {
            total += getTotalPriceByCategory(Category.NON_FOOD, nonFoodQuantity);
        }

        return total;
    }

    /**
     * Return the total price by category
     */
    private double getTotalPriceByCategory(Category category, int quantity) {
        return (category == Category.FOOD ? this.foodGoods : this.nonFoodGoods)
                .stream()
                .limit(quantity)
                .peek(good -> {
                    if (isExpirySoon(good)) {
                        double reductionPrice = (good.getPrice() * this.reductionPricePercent) / 100;
                        good.setPrice(good.getPrice() - reductionPrice);
                    }
                })
                .mapToDouble(IGood::getPrice)
                .sum();
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
        } catch (NotEnoughGoodsByCategoryException e) {
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

    private boolean isExpired(IGood good) {
        return ChronoUnit
                .DAYS
                .between(LocalDateTime.now(), good.getExpiryDate()) < 0;
    }

    /**
     * Remove goods if they are with expired date
     */
    private void removeExpiredGoods(Category category) {
        (category == Category.FOOD ? this.foodGoods : this.nonFoodGoods).removeIf(this::isExpired);
    }
}
