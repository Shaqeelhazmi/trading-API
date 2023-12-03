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
        priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
    }

    @Test
    void getDailyPriceHistory() {
        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        assertEquals(dailyPriceHistory, priceHistory.getDailyPriceHistory());
    }

    @Test
    void getWeeklyPriceHistory() {
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        assertEquals(weeklyPriceHistory, priceHistory.getWeeklyPriceHistory());
    }

    @Test
    void getMonthlyPriceHistory() {
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        assertEquals(monthlyPriceHistory, priceHistory.getMonthlyPriceHistory());
    }
}