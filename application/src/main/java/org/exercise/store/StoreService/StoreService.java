package org.exercise.store.StoreService;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.paydesk.PayDesk;
import org.exercise.store.StoreCashiersService.IStoreCashiersService;
import org.exercise.store.StoreCashiersService.StoreCashiersService;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreGoodsService.StoreGoodsService;
import org.exercise.store.StorePaydesksService.IStorePaydesksService;
import org.exercise.store.StorePaydesksService.StorePaydesksService;
import org.exercise.warehouse.IWarehouse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService implements IStoreService {
    private IStorePaydesksService storePaydesksService;
    private IStoreCashiersService storeCashiersService;
    private IStoreGoodsService storeGoodsService;

    public StoreService(int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.storePaydesksService = new StorePaydesksService();
        this.storeCashiersService = new StoreCashiersService();
        this.storeGoodsService = new StoreGoodsService(nonFoodOverpricePercent, foodOverpricePercent, reductionPricePercent, daysBeforeExpiryDate);
    }

    /**
     * Business logic for @Goods
     */
    public void addGood(IWarehouse warehouse, Category category) {
        try {
            this.storeGoodsService.addGood(warehouse, category);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGoods(IWarehouse warehouse, Category category, int quantity) {
        try {
            this.storeGoodsService.addGoods(warehouse, category, quantity);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<IGood> getFoodGoods() {
        return this.storeGoodsService.getFoodGoods();
    }

    public List<IGood> getNonFoodGoods() {
        return this.storeGoodsService.getNonFoodGoods();
    }

    /**
     * Business logic for @PayDesks
     */
    public void buildPayDesk() {
        this.storePaydesksService.buildPayDesk();
        System.out.println("You created new PayDesk!");
    }

    public HashSet<IPayDesk> getPayDesks() {
        return this.storePaydesksService.getPayDesks();
    }

    public IPayDesk getPayDeskById(int id) {
        IPayDesk payDesk = this.storePaydesksService.getPayDeskById(id);

        if (payDesk != null) {
            return payDesk;
        } else {
            throw new NoSuchElementException("There is no pay-desk with this id!");
        }
    }

    /**
     * Business logic for @Cashiers
     */
    public void hireCashier(ICashier cashier) {
        this.storeCashiersService.hireCashier(cashier);
    }

    public ICashier getCashierById(int id) {
        ICashier cashier = this.storeCashiersService.getCashierById(id);

        if (cashier != null) {
            return cashier;
        } else {
            throw new NoSuchElementException("There is no cashier with this id!");
        }
    }

    public ICashier getCashierByName(String name) {
        ICashier cashier = this.storeCashiersService.getCashierByName(name);

        if (cashier != null) {
            return cashier;
        } else {
            throw new NoSuchElementException("There is no cashier with this name!");
        }
    }

    public void addCashierToPayDesk(IPayDesk payDesk, ICashier cashier) {
        this.storeCashiersService.addCashierToPayDesk(payDesk, cashier);
    }

    public void removeCashierFromPayDesk(IPayDesk payDesk, ICashier cashier) {
        this.storeCashiersService.removeCashierFromPayDesk(payDesk, cashier);
    }

    public void sellGoods(int foodQuantity, int nonFoodQuantity, double clientMoney, int payDeskId) {
        try {
            IPayDesk payDesk = this.getPayDeskById(payDeskId);

            if(payDesk.getCashier() == null) {
                throw new NoSuchElementException("There is no cashier on this pay-desk!");
            }

            this.storeGoodsService.sellGoods(foodQuantity, nonFoodQuantity, clientMoney, payDesk);
        }
        catch (IndexOutOfBoundsException
               | NoSuchElementException
               | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
