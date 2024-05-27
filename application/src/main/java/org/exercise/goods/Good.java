package org.exercise.goods;

import java.util.Date;

public class Good implements IGood {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private Date expiryDate;
    private double deliveryPrice;
    private double salePrice;
    private Category category;

    public Good(String name, Date expiryDate, double price, Category category) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.deliveryPrice = price;
        this.salePrice = price;
        this.category = category;

        //Generate ID
        this.id = generateId();
    }

    private synchronized static int generateId() {
        return idCounter++;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public double getDeliveryPrice() {
        return this.deliveryPrice;
    }

    @Override
    public double getSalePrice() {
        return this.salePrice;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expiryDate=" + expiryDate +
                ", deliveryPrice=" + deliveryPrice +
                ", salePrice=" + salePrice +
                ", category=" + category +
                '}';
    }
}
