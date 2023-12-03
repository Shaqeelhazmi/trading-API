package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    private Stock stock;
    private PriceHistory priceHistory;
    private String stockSymbol = "TSLA";
    private String stockName = "Tesla Inc";

    @BeforeEach
    void setUp() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("2023-11-10", 130.0);
        weeklyPriceHistory.put("2020-08-07", 1491.0);
        monthlyPriceHistory.put("2014-12-31", 545.0);

        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        stock = new Stock(stockSymbol, stockName, priceHistory);
    }

    @AfterEach
    void tearDown() {
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
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("2023-11-10", 130.0);
        weeklyPriceHistory.put("2020-08-07", 1491.0);
        monthlyPriceHistory.put("2014-12-31", 545.0);
        PriceHistory priceHistory2 = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        assertEquals(priceHistory2, stock.getPriceHistory());
    }

    @Test
    void setPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("2023-11-09", 150.0);
        weeklyPriceHistory.put("2020-08-14", 2491.0);
        monthlyPriceHistory.put("2014-11-31", 645.0);
        PriceHistory priceHistory3 = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        stock.setPriceHistory(priceHistory3);
        assertEquals(priceHistory3, stock.getPriceHistory());
    }
}