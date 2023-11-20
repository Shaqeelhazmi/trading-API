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
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("TSLA", 10.0);
        weeklyPriceHistory.put("AMZN", 150.0);
        monthlyPriceHistory.put("AAPL", 300.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
    }

    @Test
    void getDailyPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("TSLA", 10.0);
        assertEquals(dailyPriceHistory, priceHistory.getDailyPriceHistory());
    }

    @Test
    void getWeeklyPriceHistory() {
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        weeklyPriceHistory.put("AMZN", 150.0);
        assertEquals(weeklyPriceHistory, priceHistory.getWeeklyPriceHistory());
    }

    @Test
    void getMonthlyPriceHistory() {
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        monthlyPriceHistory.put("AAPL", 300.0);
        assertEquals(monthlyPriceHistory, priceHistory.getMonthlyPriceHistory());
    }
}