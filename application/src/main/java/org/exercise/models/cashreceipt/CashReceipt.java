package org.exercise.models.cashreceipt;

import org.exercise.models.goods.IGood;

import java.time.LocalDateTime;
import java.util.List;

public class CashReceipt implements ICashReceipt {
    private static int idCounter = 1;
    private final int id;
    String cashierName;
    LocalDateTime receiptDate;
    List<IGood> foodGoods;
    List<IGood> nonFoodGoods;
    double totalProfit;

    public CashReceipt(String cashierName, LocalDateTime receiptDate, List<IGood> foodGoods, List<IGood> nonFoodGoods, double totalProfit) {
        this.id = idCounter++;
        this.cashierName = cashierName;
        this.receiptDate = receiptDate;
        this.foodGoods = foodGoods;
        this.nonFoodGoods = nonFoodGoods;
        this.totalProfit = totalProfit;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCashierName() {
        return cashierName;
    }

    @Override
    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    @Override
    public List<IGood> getFoodGoods() {
        return foodGoods;
    }

    @Override
    public List<IGood> getNonFoodGoods() {
        return nonFoodGoods;
    }

    @Override
    public double getTotalProfit() {
        return totalProfit;
    }

    @Override
    public String toString() {
        return "CashReceipt{" +
                "id=" + id +
                ", cashierName='" + cashierName + '\'' +
                ", receiptDate=" + receiptDate +
                ", foodGoods=" + foodGoods +
                ", nonFoodGoods=" + nonFoodGoods +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
