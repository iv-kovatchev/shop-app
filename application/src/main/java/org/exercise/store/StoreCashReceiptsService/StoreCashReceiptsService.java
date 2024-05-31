package org.exercise.store.StoreCashReceiptsService;

import org.exercise.cashier.ICashier;
import org.exercise.cashreceipt.ICashReceipt;

import java.util.HashSet;

public class StoreCashReceiptsService implements IStoreCashReceiptsService {
    private final HashSet<ICashReceipt> cashReceipts;

    public StoreCashReceiptsService() {
        this.cashReceipts = new HashSet<>();
    }

    public void addCashReceipt(ICashReceipt cashReceipt) {
        this.cashReceipts.add(cashReceipt);
    }

    public HashSet<ICashReceipt> getCashReceipts() {
        return cashReceipts;
    }
}
