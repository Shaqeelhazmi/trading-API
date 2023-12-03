package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StockInterfaceTest {

    private Stock stock;

    private PriceHistory priceHistory;


    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        priceHistory = new PriceHistory(daily, weekly, monthly);
        stock = new Stock("TSLA", "Tesla Inc", priceHistory);
    }
    @Test
    void getStockSymbol() {
        assertEquals("TSLA", stock.getStockSymbol());
    }

    @Test
    void getStockName() {
        assertEquals("Tesla Inc", stock.getStockName());
    }

    @Test
    void getPriceHistory() {
        assertEquals(priceHistory, stock.getPriceHistory());
    }

}