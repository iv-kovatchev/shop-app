package org.exercise.warehouse;

import org.exercise.goods.Category;
import org.exercise.goods.IGoods;

import java.util.Date;
import java.util.List;

public interface IWarehouse {
    IGoods createGoods(String name, Date expiryDate, double price, Category category);
    List<IGoods> getAllGoods();
    IGoods removeGoodsByCategory(Category category);
}
