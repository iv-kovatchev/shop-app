package org.exercise.store.StoreService;

import org.exercise.cashier.Cashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.store.StoreCashiersService.StoreCashiersService;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreGoodsService.StoreGoodsService;
import org.exercise.store.StorePaydesksService.StorePaydesksService;
import org.exercise.warehouse.IWarehouse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService implements IStoreService {
    private StorePaydesksService storePaydesksService;
    private StoreCashiersService storeCashiersService;
    private IStoreGoodsService storeGoodsService;

    public StoreService(int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.storePaydesksService = new StorePaydesksService();
        this.storeCashiersService = new StoreCashiersService();
        this.storeGoodsService = new StoreGoodsService(nonFoodOverpricePercent, foodOverpricePercent, reductionPricePercent, daysBeforeExpiryDate);
    }

    /**
     * Business logic for @Goods
     */
    public void addGood(Category category, IWarehouse warehouse) {
        try {
            this.storeGoodsService.addGood(category, warehouse);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGoods(Category category, IWarehouse warehouse, int quantity) {
        try {
            this.storeGoodsService.addGoods(category, warehouse, quantity);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<IGood> getGoods() {
        return this.storeGoodsService.getGoods();
    }

    /**
     * Business logic for @PayDesks
     */
    public void buildPayDesk() {
        this.storePaydesksService.buildPayDesk();
        System.out.println("You created new PayDesk in ");
    }

    public HashSet<PayDesk> getPayDesks() {
        return this.storePaydesksService.getPayDesks();
    }

    /**
     * Business logic for @Cashiers
     */
    public void addCashierToPayDesk(PayDesk payDesk, Cashier cashier) {
        if(storePaydesksService.getPayDesks().contains(payDesk)) {
            if(payDesk.getCashier() == null
                    && this.storeCashiersService.getCashiers().contains(cashier)) {
                payDesk.setCashier(cashier);
            }
            else {
                System.out.println("There is already a cashier in the pay desk!");
            }
        }
        else {
            System.out.println("The pay desk doesn't exist!");
        }
    }

    public void removeCashierFromPayDesk(PayDesk payDesk) {
        if(storePaydesksService.getPayDesks().contains(payDesk)) {
            payDesk.setCashier(null);
        }
        else {
            System.out.println("The pay desk doesn't exist!");
        }
    }

    public void firedCashier(Cashier cashier) {
        if(this.storeCashiersService.getCashiers().contains(cashier)){
            Iterator<PayDesk> payDesks = this.storePaydesksService.getPayDesks().iterator();
            boolean isCashierRemovedFromPayDesk = false;

            while(payDesks.hasNext() && !isCashierRemovedFromPayDesk) {
                PayDesk currentPayDesk = payDesks.next();

                if(currentPayDesk.getCashier() == cashier){
                    isCashierRemovedFromPayDesk = true;
                    currentPayDesk.setCashier(null);
                }
            }

            this.storeCashiersService.getCashiers().remove(cashier);
        }
        else {
            System.out.println("There is no cashier with that name!");
        }
    }
}
