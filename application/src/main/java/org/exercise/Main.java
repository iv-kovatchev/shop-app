package org.exercise;

import org.exercise.goods.Category;
import org.exercise.goods.Good;
import org.exercise.goods.IGood;
import org.exercise.paydesk.PayDesk;
import org.exercise.store.Store;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StoreGoodsService.StoreGoodsService;
import org.exercise.store.StorePaydesksService.StorePaydesksService;
import org.exercise.store.StoreService;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Date date = new Calendar.Builder()
                .setDate(2024, 7, 21)
                .build().getTime();

        System.out.println(date);
        // Create a SimpleDateFormat instance with the desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // Format the date
        String formattedDate = dateFormat.format(date);

        IWarehouse warehouse1 = new Warehouse("Warehouse #1");

        IGood good1 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good2 = warehouse1.createGood("Banana", date, 2.22, Category.FOOD);
        IGood good3 = warehouse1.createGood("Ferrari", date, 1333332.22, Category.NON_FOOD);
        IGood good4 =  warehouse1.createGood("LG Screen", date, 222.22, Category.NON_FOOD);

        List<IGood> goods = warehouse1.getAllGoods();

        /*for (IGood good : goods) {
            System.out.println(good.toString());
        }*/

        IStoreGoodsService storeGoodsService = new StoreGoodsService(10, 15, 5, 10);



        for (IGood good : storeGoodsService.getGoods()) {
            System.out.println(good.toString());
        }

        Store store = new Store("Fantastico", storeGoodsService);
        store.addGood(Category.FOOD, warehouse1);
        store.addGood(Category.FOOD, warehouse1);
        store.addGood(Category.FOOD, warehouse1);
        store.addGood(Category.NON_FOOD, warehouse1);

        for (IGood good : storeGoodsService.getGoods()) {
            System.out.println(good.toString());
        }

        PayDesk payDesk1 = new PayDesk();
        PayDesk payDesk2 = new PayDesk();
        PayDesk payDesk3 = new PayDesk();

        StorePaydesksService storePaydesksService = new StorePaydesksService();
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk1);
        storePaydesksService.buildPayDesk(payDesk2);

        for(PayDesk desk: storePaydesksService.getPayDesks()) {
            System.out.println(desk.toString());
        }

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