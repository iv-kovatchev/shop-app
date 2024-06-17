package org.exercise.store.StoreService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreCashReceiptsService.IStoreCashReceiptsService;
import org.exercise.store.StoreCashReceiptsService.StoreCashReceiptsService;
import org.exercise.store.StoreCashiersService.IStoreCashiersService;
import org.exercise.store.StoreCashiersService.StoreCashiersService;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreGoodsService.StoreGoodsService;
import org.exercise.store.StorePaydesksService.IStorePaydesksService;
import org.exercise.store.StorePaydesksService.StorePaydesksService;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService implements IStoreService {
    private IStorePaydesksService storePaydesksService;
    private IStoreCashiersService storeCashiersService;
    private IStoreGoodsService storeGoodsService;
    private IStoreCashReceiptsService storeCashReceiptsService;

    public StoreService(int nonFoodOverpricePercent, int foodOverpricePercent, int reductionPricePercent, int daysBeforeExpiryDate) {
        this.storePaydesksService = new StorePaydesksService();
        this.storeCashiersService = new StoreCashiersService();
        this.storeGoodsService = new StoreGoodsService(nonFoodOverpricePercent, foodOverpricePercent, reductionPricePercent, daysBeforeExpiryDate);
        this.storeCashReceiptsService = new StoreCashReceiptsService();
    }

    /**
     * Business logic for @Goods
     */
    public void deliverGood(IWarehouse warehouse, Category category) {
        try {
            this.storeGoodsService.addGood(warehouse, category);
        } catch (NotEnoughGoodsByCategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deliverGoods(IWarehouse warehouse, Category category, int quantity) {
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
        return this.storePaydesksService.getPayDeskById(id);
    }

    /**
     * Business logic for @Cashiers
     */
    public void hireCashier(ICashier cashier) {
        this.storeCashiersService.hireCashier(cashier);
    }

    public ICashier getCashierById(int id) {
        return this.storeCashiersService.getCashierById(id);
    }

    public ICashier getCashierByName(String name) {
        return this.storeCashiersService.getCashierByName(name);
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
