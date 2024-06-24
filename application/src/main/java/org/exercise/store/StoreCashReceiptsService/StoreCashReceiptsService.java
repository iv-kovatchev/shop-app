package org.exercise.store.StoreCashReceiptsService;

import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

public class StoreCashReceiptsService implements IStoreCashReceiptsService {
    private final HashSet<ICashReceipt> cashReceipts;

    private int cashReceiptCounter = 0;

    public StoreCashReceiptsService() {
        this.cashReceipts = new HashSet<>();
    }

    public int getCashReceiptCounter() {
        return cashReceiptCounter;
    }

    public void addCashReceipt(ICashReceipt cashReceipt) {
        this.cashReceipts.add(cashReceipt);
        this.saveCashReceiptToFile(cashReceipt);

        cashReceiptCounter++;
    }

    public HashSet<ICashReceipt> getCashReceipts() {
        return cashReceipts;
    }

    private void saveCashReceiptToFile(ICashReceipt cashReceipt) {
        try(FileWriter fw = new FileWriter("cash-receipts/cash_receipt_" + cashReceipt.getId() + ".txt")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            fw.append("Cash receipt #")
                    .append(String.valueOf(cashReceipt.getId()))
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
            fw.append("Cashier Name: ").append(cashReceipt.getCashierName())
                    .append(System.lineSeparator())
                    .append("Created Date: ").append(cashReceipt.getReceiptDate().format(formatter))
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
            fw.append("Goods: ").append(System.lineSeparator());
            fw.append(getSoldGoods(cashReceipt.getFoodGoods(), cashReceipt.getNonFoodGoods()));
            fw.append("-".repeat(50))
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
            fw.append("Total: ")
                    .append(String.format("%.2f", cashReceipt.getTotalProfit()))
                    .append("$");

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getSoldGoods(List<IGood> foodGoods, List<IGood> nonFoodGoods) {
        return String.valueOf(appendGoodsByCategory(foodGoods, Category.FOOD)) +
                appendGoodsByCategory(nonFoodGoods, Category.NON_FOOD);
    }

    private StringBuilder appendGoodsByCategory(List<IGood> goods, Category category) {
        StringBuilder allGoods = new StringBuilder();

        for (IGood good : goods) {
            allGoods.append("Name: ").append(good.getName()).append(System.lineSeparator());
            allGoods.append("Category: ")
                    .append(category == Category.FOOD ? "Food" : "Non Food")
                    .append(System.lineSeparator());
            allGoods.append("Price: ")
                    .append(String.format("%.2f", good.getPrice()))
                    .append("$")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }

        return allGoods;
    }
}
