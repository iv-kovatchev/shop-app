package org.exercise.store.StoreService;

import org.exercise.models.goods.IGood;
import org.exercise.store.StoreCashReceiptsService.IStoreCashReceiptsService;
import org.exercise.store.StoreCashiersService.IStoreCashiersService;
import org.exercise.store.StoreGoodsService.IStoreGoodsService;
import org.exercise.store.StorePaydesksService.IStorePaydesksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StoreServiceTest {
    private IStorePaydesksService storePaydesksService;
    private IStoreCashiersService storeCashiersService;
    private IStoreGoodsService storeGoodsService;
    private IStoreCashReceiptsService storeCashReceiptsService;

    private List<IGood> foodGoods;
    private List<IGood> nonFoodGoods;

    @Mock
    IGood foodGood1;

    @Mock
    IGood foodGood2;

    @Mock
    IGood nonFoodGood1;

    @Mock
    IGood nonFoodGood2;

    @BeforeEach
    void setUp() {
        when(foodGood1.getName()).thenReturn("Apple");
        when(foodGood1.getPrice()).thenReturn(4.2);

        when(foodGood2.getName()).thenReturn("Banana");
        when(foodGood2.getPrice()).thenReturn(3.33);

        when(nonFoodGood1.getName()).thenReturn("Pen");
        when(nonFoodGood1.getPrice()).thenReturn(1.42);

        when(nonFoodGood2.getName()).thenReturn("Notebook");
        when(nonFoodGood2.getPrice()).thenReturn(5.6);

        foodGoods = new ArrayList<>();
        foodGoods.add(foodGood1);
        foodGoods.add(foodGood2);

        nonFoodGoods = new ArrayList<>();
        nonFoodGoods.add(nonFoodGood1);
        nonFoodGoods.add(nonFoodGood2);
    }

    @Test
    void deliverGood() {
    }

    @Test
    void deliverGoods() {
    }

    @Test
    void getFoodGoods() {
    }

    @Test
    void getNonFoodGoods() {
    }

    @Test
    void buildPayDesk() {
    }

    @Test
    void getPayDesks() {
    }

    @Test
    void getPayDeskById() {
    }

    @Test
    void hireCashier() {
    }

    @Test
    void getCashierById() {
    }

    @Test
    void getCashierByName() {
    }

    @Test
    void addCashierToPayDesk() {
    }

    @Test
    void removeCashierFromPayDesk() {
    }

    @Test
    void sellGoods() {
    }

    @Test
    void storeInfo() {
    }

    @Test
    void deserializeGoods() {
    }
}