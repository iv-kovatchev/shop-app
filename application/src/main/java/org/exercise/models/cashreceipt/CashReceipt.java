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
