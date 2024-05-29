package org.exercise.warehouse;

import org.exercise.goods.Category;
import org.exercise.goods.IGood;

import java.util.Date;
import java.util.List;

public interface IWarehouse {
    IGood createGood(String name, Date expiryDate, double price, Category category);

    List<IGood> getAllGoods();

    List<IGood> getAllGoodsByCategory(Category category);

    List<IGood> getNumberOfGoodsByCategory(Category category, int quantity);

    void removeGood(IGood good);
}
