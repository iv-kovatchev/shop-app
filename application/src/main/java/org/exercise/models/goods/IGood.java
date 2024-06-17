package org.exercise.models.goods;

import java.time.LocalDateTime;

public interface IGood {
    public int getId();
    public String getName();
    public LocalDateTime getExpiryDate();
    public double getPrice();
    public Category getCategory();

    void setName(String name);
    void setExpiryDate(LocalDateTime expiryDate);
    void setPrice(double price);
    void setCategory(Category category);
}
