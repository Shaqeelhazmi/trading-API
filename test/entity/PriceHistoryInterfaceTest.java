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
        dailyPriceHistory.put("2023-11-10", 130.0);
        weeklyPriceHistory.put("2020-08-07", 1491.0);
        monthlyPriceHistory.put("2014-12-31", 545.0);
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
    }

    @Test
    void getDailyPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("2023-11-10", 130.0);
        assertEquals(dailyPriceHistory, priceHistory.getDailyPriceHistory());
    }

    @Test
    void getWeeklyPriceHistory() {
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        weeklyPriceHistory.put("2020-08-07", 1491.0);
        assertEquals(weeklyPriceHistory, priceHistory.getWeeklyPriceHistory());
    }

    @Test
    void getMonthlyPriceHistory() {
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        monthlyPriceHistory.put("2014-12-31", 545.0);
        assertEquals(monthlyPriceHistory, priceHistory.getMonthlyPriceHistory());
    }
}