package org.exercise.warehouse;

import org.exercise.models.goods.Category;
import org.exercise.models.goods.Good;
import org.exercise.models.goods.IGood;
import org.exercise.warehouse.exceptions.GoodNotFoundException;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.time.LocalDateTime;
import java.util.*;

public class Warehouse implements IWarehouse {
    private String name;
    private final List<IGood> goodsList;

    public Warehouse(String name) {
        this.name = name;
        this.goodsList = new ArrayList<>();
    }

    @Override
    public IGood createGood(String name, LocalDateTime expiryDate, double price, Category category) {
        IGood good = new Good(name, expiryDate, price, category);
        this.goodsList.add(good);
        return good;
    }

    @Override
    public void createGoods(String name, LocalDateTime expiryDate, double price, Category category, int quantity) {
        for(int i = 0; i < quantity; i++) {
            IGood good = new Good(name, expiryDate, price, category);
            this.goodsList.add(good);
        }
    }

    @Override
    public List<IGood> getAllGoods() {
        return this.goodsList;
    }

    @Override
    public List<IGood> getAllGoodsByCategory(Category category) throws NotEnoughGoodsByCategoryException {
        List<IGood> goods = this.goodsList.stream()
                .filter(g -> g.getCategory() == category)
                .toList();

        if(!goods.isEmpty()) {
            return goods;
        }
        else {
            throw new NotEnoughGoodsByCategoryException("There is not enough goods by this category in the warehouse!");
        }
    }

    @Override
    public List<IGood> getNumberOfGoodsByCategory(Category category, int quantity) throws NotEnoughGoodsByCategoryException {
        List<IGood> goods = this.goodsList.stream()
                .filter(g -> g.getCategory() == category).limit(quantity)
                .toList();

        if(goods.size() == quantity) {
            return goods;
        }
        else {
            throw new NotEnoughGoodsByCategoryException("There is not enough goods by this category in the warehouse!");
        }
    }

    @Override
    public void removeGood(IGood good) throws GoodNotFoundException {
        if(this.goodsList.contains(good)) {
            goodsList.remove(good);
            System.out.println("Removed good: " + good);
        }
        else {
            throw new GoodNotFoundException("Good not found!");
        }
    }
}
