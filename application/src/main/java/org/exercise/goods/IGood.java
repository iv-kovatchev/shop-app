package org.exercise.goods;

import java.util.Date;

public interface IGood {
    public int getId();
    public String getName();
    public Date getExpiryDate();
    public double getDeliveryPrice();
    public double getSalePrice();
    public Category getCategory();

    void setName(String name);
    void setExpiryDate(Date expiryDate);
    void setDeliveryPrice(double deliveryPrice);
    void setSalePrice(double salePrice);
    void setCategory(Category category);
}
