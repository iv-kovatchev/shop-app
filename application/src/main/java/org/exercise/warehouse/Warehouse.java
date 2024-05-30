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
        List<IGood> goods = this.goodsList.stream()
                .filter(g -> g.getCategory() == category)
                .toList();

        if(!goods.isEmpty()) {
            return goods;
        }
        else {
            throw new NoSuchElementException("There is not enough goods from this category in the warehouse!");
        }
    }

    @Override
    public List<IGood> getNumberOfGoodsByCategory(Category category, int quantity) {
        List<IGood> goods = this.goodsList.stream()
                .filter(g -> g.getCategory() == category).limit(quantity)
                .toList();

        if(goods.size() == quantity) {
            return goods;
        }
        else {
            throw new NoSuchElementException("There is not enough goods from this category in the warehouse!");
        }
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
}
