package org.exercise.warehouse;

import org.exercise.models.goods.Category;
import org.exercise.models.goods.Good;
import org.exercise.models.goods.IGood;
import org.exercise.warehouse.exceptions.GoodNotFoundException;
import org.exercise.warehouse.exceptions.NotEnoughGoodsByCategoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        String name = "Milk";
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(10);
        double price = 2.99;
        Category category = Category.FOOD;

        warehouse = new Warehouse("Test");
        warehouse.createGoods(name, expiryDate, price, category, 3);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Create new good in the warehouse")
    void createGood() {
        String name = "Milk";
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(10);
        double price = 2.99;
        Category category = Category.FOOD;

        IGood good = warehouse.createGood(name, expiryDate, price, category);

        assertNotNull(good);
        assertEquals(name, good.getName());
        assertEquals(expiryDate, good.getExpiryDate());
        assertEquals(price, good.getPrice());
        assertEquals(category, good.getCategory());
    }

    @Test
    @DisplayName("Create more than one good in the warehouse, we add quantity")
    void createGoods() {
        String expectedName = "Apple";
        LocalDateTime expectedExpiryDate = LocalDateTime.now().plusDays(10);
        double expectedPrice = 2.99;
        Category expectedCategory = Category.FOOD;

        warehouse.createGoods(expectedName, expectedExpiryDate, expectedPrice, expectedCategory, 7);
        IGood good = null;

        /* We start from 3 because we already had 3 goods in our warehouse */
        for(int i = 3; i < 10;  i++) {
            good = warehouse.getGoodsList().get(i);

            assertNotNull(good);
            assertEquals(expectedName, good.getName());
            assertEquals(expectedExpiryDate, good.getExpiryDate());
            assertEquals(expectedPrice, good.getPrice());
            assertEquals(expectedCategory, good.getCategory());
        }
    }

    @Test
    @DisplayName("Return the expected number of goods which we have in the warehouse")
    void getAllGoods() {
        int expectedSize = 3;
        assertEquals(expectedSize, warehouse.getAllGoods().size());
    }

    @Test
    @DisplayName("Return the expected number of goods by category which we have in the warehouse")
    void getAllGoodsByCategory() throws NotEnoughGoodsByCategoryException {
        int expectedSize = 3;
        assertEquals(expectedSize, warehouse.getAllGoodsByCategory(Category.FOOD).size());
    }

    @Test
    void removeGood() throws GoodNotFoundException {
        int expectedSize = 2;
        IGood good = warehouse.getGoodsList().get(2);
        warehouse.removeGood(good);

        assertEquals(expectedSize, warehouse.getGoodsList().size());
        assertEquals("Removed good: " + good, outContent.toString().trim());
    }

    @Test
    void removeGoodThrowsGoodNotFoundException() {
        //1sec 555ms, Mockito object makes the test slower
        IGood notExistingGood = Mockito.mock(IGood.class);

        GoodNotFoundException exc = assertThrows(GoodNotFoundException.class,
                () -> warehouse.removeGood(notExistingGood));
        assertEquals("Good not found!", exc.getMessage());
    }
}