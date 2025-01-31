package org.exercise;

import org.exercise.models.cashier.Cashier;
import org.exercise.models.cashier.ICashier;
import org.exercise.models.goods.Category;
import org.exercise.models.goods.IGood;
import org.exercise.store.Store;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Create three random dates
        LocalDateTime date = LocalDateTime.of(2024, 6, 22, 20, 55, 44);
        LocalDateTime date1 = LocalDateTime.of(2024, 8, 11, 18, 33, 11);

        //Create new Warehouse object
        IWarehouse warehouse1 = new Warehouse("Warehouse #1");

        //Create six Goods objects
        IGood good1 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good2 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good5 = warehouse1.createGood("Banana", date1, 2.22, Category.FOOD);
        IGood good7 = warehouse1.createGood("PC", date1, 2213.22, Category.NON_FOOD);
        IGood good3 = warehouse1.createGood("Ferrari", date1, 1333332.22, Category.NON_FOOD);
        IGood good4 = warehouse1.createGood("LG Screen", date, 222.22, Category.NON_FOOD);

        //Get all goods from warehouse
        List<IGood> goods = warehouse1.getAllGoods();
        System.out.println("The goods in \"Warehouse #1\" are: ");

        for (IGood good : goods) {
            System.out.println(good.toString());
        }

        System.out.println("\n------------------------------------------\n");

        //Create new store and deliver goods from the warehouse
        Store store = new Store("Fantastico", 10, 15, 15, 10);
        store.deliverGood(warehouse1, Category.FOOD);
        store.deliverGood(warehouse1, Category.FOOD);
        store.deliverGood(warehouse1, Category.FOOD);
        store.deliverGoods(warehouse1, Category.NON_FOOD, 2);

        //Print current goods in the store
        System.out.println("\n------------------------------------------\n");
        System.out.println("The goods in \"Fantastiko\" are: ");

        for (IGood good : store.getFoodGoods()) {
            System.out.println(good.toString());
        }

        for (IGood good : store.getNonFoodGoods()) {
            System.out.println(good.toString());
        }

        System.out.println("\n------------------------------------------\n");

        //We don't have any pay-desks, that's why we build 4
        store.buildPayDesk();
        store.buildPayDesk();
        store.buildPayDesk();
        store.buildPayDesk();

        //We create 3 new cashiers, who will find job soon
        ICashier cashier1 = new Cashier("Ivan", 2200.0);
        ICashier cashier2 = new Cashier("Rayna", 2500.0);
        ICashier cashier3 = new Cashier("Peter", 1100.0);

        //We hire the first 2 cashiers to our store 'Fantastico'
        store.hireCashier(cashier1);
        store.hireCashier(cashier2);

        //We try to hire cashier who is working already
        store.hireCashier(cashier2);

        //Add cashier to paydesk
        store.addCashierToPayDesk(1, 1);

        //Find existing cashier
        ICashier foundCashier2 = store.getCashierByName("Rayna");
        store.addCashierToPayDesk(1, 2);

        System.out.println("\n------------------------------------------\n");
        store.sellGoods(2, 0, 4000.0, 1);
        System.out.println("\n");
        store.sellGoods(2, 0, 4000.0, 1);

        store.deliverGood(warehouse1, Category.FOOD);
        store.deliverGood(warehouse1, Category.NON_FOOD);
        store.deliverGoods(warehouse1, Category.FOOD, 10);

        warehouse1.createGood("Kiwi", date1, 1, Category.FOOD);
        warehouse1.createGoods("Kiwi", date1, 1, Category.FOOD, 10);

        store.deliverGood(warehouse1, Category.FOOD);
        store.deliverGoods(warehouse1, Category.FOOD, 10);

        //sell goods to clients
        store.sellGoods(5, 0, 4000.0, 1);
        store.sellGoods(5, 0, 4000.0, 1);

        //Information about the expense and profit of the store
        store.storeInfo();

        System.out.println();
        store.sellGoods(0, 2, 4444444000.0, 1);

        store.storeInfo();

        //Deserialize goods
        /*System.out.println("\n------------------------------------------\n");
        System.out.println("Deserialize goods\n");
        store.deserializeGoods();*/
    }
}