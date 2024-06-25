package org.exercise.store.StoreGoodsService;

import org.exercise.models.cashier.ICashier;
import org.exercise.models.goods.Category;
import org.exercise.models.paydesk.IPayDesk;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughGoodsException;
import org.exercise.store.StoreGoodsService.exceptions.NotEnoughMoneyException;
import org.exercise.warehouse.IWarehouse;
import org.exercise.warehouse.Warehouse;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StoreGoodsServiceTest {
    private IStoreGoodsService storeGoodsService;
    private IWarehouse warehouseSpy;
    private IPayDesk mockPaydesk;

    @BeforeEach
    void setUp() {
        storeGoodsService = new StoreGoodsService(20, 10, 5, 7);
        warehouseSpy = Mockito.spy(new Warehouse("Test Warehouse"));

        warehouseSpy.createGoods("Test Food Good", LocalDateTime.now().plusDays(10), 2.15, Category.FOOD, 5);
        warehouseSpy.createGoods("Test Non-Food Good", LocalDateTime.now().plusDays(10), 2.15, Category.NON_FOOD, 5);
        //Add goods with expire date
        warehouseSpy.createGood("Test Non-Food Good", LocalDateTime.now().minusDays(10), 2.15, Category.NON_FOOD);
        warehouseSpy.createGood("Test Non-Food Good", LocalDateTime.now().minusDays(10), 2.15, Category.NON_FOOD);

        ICashier mockCashier = Mockito.mock(ICashier.class);
        when(mockCashier.getId()).thenReturn(1);
        when(mockCashier.getName()).thenReturn("Ivan");

        mockPaydesk = Mockito.mock(IPayDesk.class);
        when(mockPaydesk.getId()).thenReturn(1);
        when(mockPaydesk.getCashier()).thenReturn(mockCashier);
    }

    @Test
    @DisplayName("Add new food good in our store and remove it from the warehouse which make the deliver")
    void addFoodGood() throws NotEnoughGoodsByCategoryException {
        int expectedFoodGoodsInStore = 1;
        int expectedFoodGoodsInWarehouse = 4;

        storeGoodsService.addGood(warehouseSpy, Category.FOOD);

        assertEquals(expectedFoodGoodsInStore, storeGoodsService.getFoodGoods().size());
        assertEquals(expectedFoodGoodsInWarehouse, warehouseSpy.getAllGoodsByCategory(Category.FOOD).size());
    }

    @Test
    @DisplayName("Add number of food goods in our store and remove them from the warehouse which make the deliver")
    void addFoodGoods() throws NotEnoughGoodsByCategoryException {
        int expectedFoodGoodsInStore = 4;
        int expectedFoodGoodsInWarehouse = 1;

        storeGoodsService.addGoods(warehouseSpy, Category.FOOD, 4);

        assertEquals(expectedFoodGoodsInStore, storeGoodsService.getFoodGoods().size());
        assertEquals(expectedFoodGoodsInWarehouse, warehouseSpy.getAllGoodsByCategory(Category.FOOD).size());
    }

    @Test
    @DisplayName("Add good one by one in the store from the warehouse")
    void addGood() throws NotEnoughGoodsByCategoryException {
        storeGoodsService.addGood(warehouseSpy, Category.FOOD);
        storeGoodsService.addGood(warehouseSpy, Category.FOOD);
        storeGoodsService.addGood(warehouseSpy, Category.NON_FOOD);

        assertEquals(2, storeGoodsService.getFoodGoods().size());
        assertEquals(1, storeGoodsService.getNonFoodGoods().size());
        assertEquals(9, warehouseSpy.getAllGoods().size());

        assertEquals(6.449999999999999, storeGoodsService.getTotalDeliveryCost());

    }

    @Test
    @DisplayName("Add number of goods in the store from the warehouse")
    void addGoods() throws NotEnoughGoodsByCategoryException {
        storeGoodsService.addGoods(warehouseSpy, Category.FOOD, 2);
        storeGoodsService.addGoods(warehouseSpy, Category.NON_FOOD, 2);

        assertEquals(2, storeGoodsService.getFoodGoods().size());
        assertEquals(2, storeGoodsService.getNonFoodGoods().size());
        assertEquals(8, warehouseSpy.getAllGoods().size());

        assertEquals(8.6, storeGoodsService.getTotalDeliveryCost());

    }

    @Test
    @DisplayName("Trying to add more food goods in our than the number in the warehouse and we throw exception")
    void addGoodThrowException() {
        NotEnoughGoodsByCategoryException exc = assertThrows(NotEnoughGoodsByCategoryException.class,
                () -> warehouseSpy.getNumberOfGoodsByCategory(Category.FOOD, 6));

        assertEquals("There is not enough goods by this category in the warehouse!", exc.getMessage());
    }

    @Test
    @DisplayName("Sell goods to client")
    void sellGoods() throws NotEnoughMoneyException, NotEnoughGoodsException {
        storeGoodsService.addGoods(warehouseSpy, Category.FOOD, 5);
        storeGoodsService.addGoods(warehouseSpy, Category.NON_FOOD, 6);

        storeGoodsService.sellGoods(5, 5, 50000, mockPaydesk);

        assertEquals(24.725, storeGoodsService.getTotalProfitBySoldGoods());
        assertEquals(0, storeGoodsService.getFoodGoods().size());
        assertEquals(0, storeGoodsService.getNonFoodGoods().size());
    }

    @Test
    @DisplayName("Throw exception when we try to sell goods on client who doesn't have enough money")
    void sellGoodsThrowNotEnoughMoneyException() {
        storeGoodsService.addGoods(warehouseSpy, Category.FOOD, 5);
        storeGoodsService.addGoods(warehouseSpy, Category.NON_FOOD, 6);

        NotEnoughMoneyException exc = assertThrows(NotEnoughMoneyException.class,
                () ->storeGoodsService.sellGoods(5, 5, 5, mockPaydesk));

        assertEquals("The client doesn't have enough money!", exc.getMessage());
    }

    @Test
    @DisplayName("Throw exception when we don't have enough goods in the store")
    void sellGoodsThrowNotEnoughGoodsException() {
        NotEnoughGoodsException exc = assertThrows(NotEnoughGoodsException.class,
                () ->storeGoodsService.sellGoods(5, 5, 5, mockPaydesk));

        assertEquals("There aren't enough goods!", exc.getMessage());
    }

    @Test
    @DisplayName("Sell goods with 0 quantity on both categories")
    void sellGoodsWithZeroQuantities() throws NotEnoughMoneyException, NotEnoughGoodsException {
        storeGoodsService.addGoods(warehouseSpy, Category.FOOD, 5);
        storeGoodsService.addGoods(warehouseSpy, Category.NON_FOOD, 6);

        storeGoodsService.sellGoods(0, 0, 50000, mockPaydesk);

        assertEquals(0, storeGoodsService.getTotalProfitBySoldGoods());
    }
}