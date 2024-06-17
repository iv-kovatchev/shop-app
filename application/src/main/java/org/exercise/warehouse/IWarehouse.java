package org.exercise.warehouse;

import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IWarehouse {
    /**
     * Create new Good
     */
    IGood createGood(String name, LocalDateTime expiryDate, double price, Category category);

    /**
     * Create more than one good
     */
    void createGoods(String name, LocalDateTime expiryDate, double price, Category category, int quantity);

    /**
     * Get all goods from the warehouse
     */
    List<IGood> getAllGoods();

    /**
     * Get all goods from the warehouse by category
     */
    List<IGood> getAllGoodsByCategory(Category category);

    /**
     * Get number of goods by category from the warehouse
     */
    List<IGood> getNumberOfGoodsByCategory(Category category, int quantity);

    /**
     * Remove good from the warehouse
     */
    void removeGood(IGood good);
}
