package org.exercise.models.goods;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Good implements IGood, Serializable {
    @Serial
    private static final long serialVersionUID = 123L;
    private static int idCounter = 1;
    private final int id;
    private String name;
    private LocalDateTime expiryDate;
    private double price;
    private Category category;

    public Good(String name, LocalDateTime expiryDate, double price, Category category) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.price = price;
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
    public LocalDateTime getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public double getPrice() {
        return this.price;
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
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
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
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
