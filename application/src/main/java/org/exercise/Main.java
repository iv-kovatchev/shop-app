package org.exercise;

import org.exercise.cashier.Cashier;
import org.exercise.cashier.ICashier;
import org.exercise.goods.Category;
import org.exercise.goods.IGood;
import org.exercise.paydesk.IPayDesk;
import org.exercise.store.Store;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Date date = new Calendar.Builder()
                .setDate(2024, 7, 21)
                .build().getTime();

        System.out.println(date);
        // Create a SimpleDateFormat instance with the desired pattern

        IWarehouse warehouse1 = new Warehouse("Warehouse #1");

        IGood good1 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good2 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good5 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good3 = warehouse1.createGood("Ferrari", date, 1333332.22, Category.NON_FOOD);
        IGood good4 =  warehouse1.createGood("LG Screen", date, 222.22, Category.NON_FOOD);

        List<IGood> goods = warehouse1.getAllGoods();

        for (IGood good : goods) {
            System.out.println(good.toString());
        }

        Store store = new Store("Fantastico", 10, 15, 5, 10);
        store.addGood(warehouse1, Category.FOOD);
        store.addGood(warehouse1, Category.FOOD);
        store.addGood(warehouse1, Category.FOOD);
       // store.addGood(warehouse1, Category.NON_FOOD);

        store.addGoods(warehouse1, Category.NON_FOOD, 2);

        System.out.println("The goods in \"Fantastiko\" are: ");

        for (IGood good : store.getFoodGoods()) {
            System.out.println(good.toString());
        }

        for (IGood good : store.getNonFoodGoods()) {
            System.out.println(good.toString());
        }

        store.buildPayDesk();
        store.buildPayDesk();
        store.buildPayDesk();
        store.buildPayDesk();

        ICashier cashier1 = new Cashier("Ivan", 2200.0);
        ICashier cashier2 = new Cashier("Rayna", 2500.0);
        ICashier cashier3 = new Cashier("Diyan", 1100.0);

        store.hireCashier(cashier1);
        store.hireCashier(cashier2);
        store.hireCashier(cashier2);

        try {
            ICashier foundCashier = store.getCashierByName("Ivan");
            System.out.println(foundCashier.toString());

            IPayDesk payDesk = store.getPayDeskById(1);
            store.addCashierToPayDesk(payDesk, foundCashier);

            System.out.println(payDesk);

            ICashier foundCashier2 = store.getCashierByName("Rayna");

            store.addCashierToPayDesk(payDesk, foundCashier2);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        /*StoreCashiersService storeCashiersService = new StoreCashiersService();
        StorePaydesksService storePaydesksService = new StorePaydesksService();
        StoreService storeService = new StoreService(storePaydesksService, storeGoodsService);

        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk2);

        Cashier cashier1 = new Cashier("Ivan", 2200);
        Cashier cashier2 = new Cashier("Rayna", 2250);
        Cashier cashier3 = new Cashier("Georgi", 2250);

        storeCashiersService.hireCashier(cashier1);
        storeCashiersService.hireCashier(cashier1);
        storeCashiersService.hireCashier(cashier1);
        storeCashiersService.hireCashier(cashier1);
        storeCashiersService.hireCashier(cashier1);
        storeCashiersService.hireCashier(cashier2);

        storeService.addCashierToPayDesk(payDesk1, cashier1);
        storeService.addCashierToPayDesk(payDesk1, cashier1);
        storeService.addCashierToPayDesk(payDesk33, cashier1);

        System.out.println("The paydesks are: ");

        for(PayDesk desk: storePaydesksService.getPayDesks()) {
            System.out.println(desk.toString());
        }

        storeService.removeCashierFromPayDesk(payDesk1);

        storeCashiersService.getCashiers().remove(cashier3);*/

        /*System.out.println();

        for (IGood good : goods) {
            System.out.println(good.toString());
        }*/

        /*Comparator<IGood> comparator = new Comparator<IGood>() {
            @Override
            public int compare(IGood o1, IGood o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };*/


    }
}