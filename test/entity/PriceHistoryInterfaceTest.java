package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PriceHistoryInterfaceTest {
    private PriceHistory priceHistory;
    @BeforeEach
    void setUp() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<String, Double>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<String, Double>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<String, Double>();
        dailyPriceHistory.put("TSLA", 10.0);
        weeklyPriceHistory.put("AMZN", 150.0);
        monthlyPriceHistory.put("AAPL", 300.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
    }

    @Test
    void getDailyPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<String, Double>();
        dailyPriceHistory.put("TSLA", 10.0);
        assertEquals(dailyPriceHistory, priceHistory.getDailyPriceHistory());
    }

    @Test
    void getWeeklyPriceHistory() {
        HashMap<String, Double> weeklyPriceHistory = new HashMap<String, Double>();
        weeklyPriceHistory.put("AMZN", 150.0);
        assertEquals(weeklyPriceHistory, priceHistory.getWeeklyPriceHistory());
    }

    @Test
    void getMonthlyPriceHistory() {
        HashMap<String, Double> monthlyPriceHistory = new HashMap<String, Double>();
        monthlyPriceHistory.put("AAPL", 300.0);
        assertEquals(monthlyPriceHistory, priceHistory.getMonthlyPriceHistory());
    }
}