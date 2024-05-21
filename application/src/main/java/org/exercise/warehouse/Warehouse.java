package org.exercise.warehouse;

import org.exercise.goods.Category;
import org.exercise.goods.Goods;
import org.exercise.goods.IGoods;

import java.util.*;

public class Warehouse implements IWarehouse {
    private String name;
    private List<IGoods> goodsList;

    public Warehouse(String name) {
        this.name = name;
        this.goodsList = new ArrayList<>();
    }

    @Override
    public IGoods createGoods(String name, Date expiryDate, double price, Category category) {
        IGoods goods = new Goods(name, expiryDate, price, category);
        this.goodsList.add(goods);
        return goods;
    }

    @Override
    public List<IGoods> getAllGoods() {
        return this.goodsList;
    }

    @Override
    public IGoods removeGoodsByCategory(Category category) {
        Optional<IGoods> goodsToRemove = this.goodsList.stream()
                .filter(g -> g.getCategory() == category)
                .findFirst();

        if(goodsToRemove.isPresent()) {
            this.goodsList.remove(goodsToRemove.get());
            return goodsToRemove.get();
        }
        else {
            throw new NoSuchElementException("No goods found for category: " + category);
        }
    }
}
