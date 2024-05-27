package org.exercise.store;

import org.exercise.cashier.Cashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.warehouse.Warehouse;

import java.util.*;

public class StoreService {
    private List<IGood> goods;
    private List<PayDesk> payDesks;
    private HashSet<Cashier> cashiers;
    private Map<PayDesk, Cashier> payDeskCashierMap;
    private int nonFoodOverpricePercent;
    private int foodOverpricePercent;

    public StoreService(List<PayDesk> payDesks, int nonFoodOverpricePercent, int foodOverpricePercent) {
        this.goods = new ArrayList<>();
        this.payDesks = payDesks;
        this.cashiers = new HashSet<>();
        this.payDeskCashierMap = new HashMap<>();
        this.nonFoodOverpricePercent = nonFoodOverpricePercent;
        this.foodOverpricePercent = foodOverpricePercent;
    }

    public void addGood(Category category, Warehouse warehouse) {
        IGood good = warehouse.getAllGoodsByCategory(category).getFirst();

        if(good != null) {
            overpriceGood(good);

            this.goods.add(good);
            this.goods.remove(good);
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
