package org.exercise.warehouse;

import org.exercise.goods.Category;
import org.exercise.goods.Good;
import org.exercise.goods.IGood;

import java.util.*;

public class Warehouse implements IWarehouse {
    private String name;
    private List<IGood> goodsList;

    public Warehouse(String name) {
        this.name = name;
        this.goodsList = new ArrayList<>();
    }

    @Override
    public IGood createGood(String name, Date expiryDate, double price, Category category) {
        IGood good = new Good(name, expiryDate, price, category);
        this.goodsList.add(good);
        return good;
    }

    @Override
    public List<IGood> getAllGoods() {
        return this.goodsList;
    }

    @Override
    public List<IGood> getAllGoodsByCategory(Category category) {
        /*
            PROVERI OSHtE TUKA ZA EXCEPTION
        */

        return this.goodsList.stream()
                .filter(g -> g.getCategory() == category)
                .toList();
    }

    @Override
    public void removeGood(IGood good) {
        if(this.goodsList.contains(good)) {
            goodsList.remove(good);
            System.out.println("Removed good: " + good);
        }
        else {
            throw new NoSuchElementException("No good found!");
        }
    }

    @Override
    public IGood removeGoodByCategory(Category category) {
        Optional<IGood> goodsToRemove = this.goodsList.stream()
                .filter(g -> g.getCategory() == category)
                .findFirst();

        if(goodsToRemove.isPresent()) {
            this.goodsList.remove(goodsToRemove.get());
            System.out.println("\"" + goodsToRemove.get().getName() + "\" was removed from the warehouse!");
        }
        else {
            throw new NoSuchElementException("No goods found for category: " +
                    (category.name().equals("NON_FOOD") ? "Non food" : "Food"));
        }

        return goodsToRemove.get();
    }
}
