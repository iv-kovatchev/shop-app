package org.exercise.models.cashreceipt;

import org.exercise.models.goods.IGood;

import java.time.LocalDateTime;
import java.util.List;

public interface ICashReceipt {
    int getId();

    String getCashierName();

    LocalDateTime getReceiptDate();

    List<IGood> getFoodGoods();

    List<IGood> getNonFoodGoods();

    double getTotalProfit();
}
