package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    private Stock stock;
    private PriceHistory priceHistory;
    @BeforeEach
    void setUp() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("TSLA", 10.0);
        weeklyPriceHistory.put("AMZN", 150.0);
        monthlyPriceHistory.put("AAPL", 300.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        String stockSymbol = "TSLA";
        String stockName = "Tesla";
        stock = new Stock(stockSymbol, priceHistory, stockName);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStockSymbol() {
        assertEquals("TSLA", stock.getStockSymbol());
    }

    @Test
    void getPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("TSLA", 10.0);
        weeklyPriceHistory.put("AMZN", 150.0);
        monthlyPriceHistory.put("AAPL", 300.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        assertEquals(priceHistory, stock.getPriceHistory());
    }

    @Test
    void getStockName() {
        assertEquals("Tesla", stock.getStockName());
    }

    @Test
    void setPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("TSLA", 20.0);
        weeklyPriceHistory.put("AMZN", 250.0);
        monthlyPriceHistory.put("AAPL", 100.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        stock.setPriceHistory(priceHistory);
        assertEquals(priceHistory, stock.getPriceHistory());
    }
}