package org.exercise.store.StoreService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.cashreceipt.ICashReceipt;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreCashReceiptsService.IStoreCashReceiptsService;
import org.exercise.store.StoreCashReceiptsService.StoreCashReceiptsService;
import org.exercise.store.StoreCashiersService.IStoreCashiersService;
import org.exercise.store.StoreCashiersService.StoreCashiersService;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreGoodsService.SerializeType;
import org.exercise.store.StoreGoodsService.StoreGoodsService;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughGoodsException;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughMoneyException;
import org.exercise.store.StorePaydesksService.IStorePaydesksService;
import org.exercise.store.StorePaydesksService.StorePaydesksService;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService implements IStoreService {
    private final IStorePaydesksService storePaydesksService;
    private final IStoreCashiersService storeCashiersService;
    private final IStoreGoodsService storeGoodsService;
    private final IStoreCashReceiptsService storeCashReceiptsService;

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

    public void addCashierToPayDesk(int payDeskId, int cashierId) {
        try {
            IPayDesk payDesk = this.storePaydesksService.getPayDeskById(payDeskId);
            ICashier cashier = this.storeCashiersService.getCashierById(cashierId);
            this.storeCashiersService.addCashierToPayDesk(payDesk, cashier);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
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

            ICashReceipt cashReceipt = this.storeGoodsService.sellGoods(foodQuantity, nonFoodQuantity, clientMoney, payDesk);
            this.storeCashReceiptsService.addCashReceipt(cashReceipt);
            System.out.println("The goods were sold and the total profit from them is "
                    + String.format("%.2f", cashReceipt.getTotalProfit()) + "$");

        }
        catch (NotEnoughGoodsException
               | NotEnoughMoneyException
               | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void storeInfo(String name) {
        System.out.println("\n\"" + name + "\"");
        System.out.println("------------------------------------------");

        double totalSalaries = this.storeCashiersService
                .getCashiers()
                .stream()
                .mapToDouble(ICashier::getMonthSalary).sum();
        System.out.println("Total cashier salaries: " + String.format("%.2f", totalSalaries) + "$");
        System.out.println("Total delivery cost: " + String.format("%.2f", storeGoodsService.getTotalDeliveryCost()) + "$\n");
        System.out.println("Total profit by sold goods: " + String.format("%.2f", storeGoodsService.getTotalProfitBySoldGoods()) + "$");

        System.out.println("\n------------------------------------------\n");

        System.out.println("Total profit: " +
                String.format("%.2f", (storeGoodsService.getTotalProfitBySoldGoods() - (totalSalaries + storeGoodsService.getTotalDeliveryCost()))));
    }

    public void deserializeGoods() {
        System.out.println("- Delivered Goods:");
        System.out.println("\t" + this.storeGoodsService.deserializeGood(1, SerializeType.DELIVERY));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(2, SerializeType.DELIVERY));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(3, SerializeType.DELIVERY));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(4, SerializeType.DELIVERY));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(5, SerializeType.DELIVERY));

        System.out.println("\n------------------------------------------\n");

        System.out.println("- Sold Goods:");
        System.out.println("\t" + this.storeGoodsService.deserializeGood(3, SerializeType.SOLD));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(4, SerializeType.SOLD));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(5, SerializeType.SOLD));
        System.out.println("\t" + this.storeGoodsService.deserializeGood(7, SerializeType.SOLD));
    }
}
