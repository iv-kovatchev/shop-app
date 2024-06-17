package org.exercise.store.StoreCashReceiptsService;

import org.exercise.models.cashreceipt.ICashReceipt;

import java.util.HashSet;

public interface IStoreCashReceiptsService {
    void addCashReceipt(ICashReceipt cashReceipt);

    HashSet<ICashReceipt> getCashReceipts();
}
