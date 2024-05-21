package org.exercise;

import org.exercise.goods.Category;
import org.exercise.goods.IGoods;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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

        warehouse1.createGoods("Banana", date, 2.22, Category.FOOD);
        warehouse1.createGoods("Banana", date, 2.22, Category.FOOD);
        warehouse1.createGoods("Ferrari", date, 1333332.22, Category.NON_FOOD);
        warehouse1.createGoods("LG Screen", date, 222.22, Category.NON_FOOD);

        List<IGoods> goods = warehouse1.getAllGoods();

        for (IGoods good : goods) {
            System.out.println(good.toString());
        }

        try {
            warehouse1.removeGoodsByCategory(Category.FOOD);
            warehouse1.removeGoodsByCategory(Category.NON_FOOD);
            warehouse1.removeGoodsByCategory(Category.NON_FOOD);
            warehouse1.removeGoodsByCategory(Category.NON_FOOD);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        for (IGoods good : goods) {
            System.out.println(good.toString());
        }
    }
}