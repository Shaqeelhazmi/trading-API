package entity;

import java.util.HashMap;

public class PriceHistory {
    private HashMap<String, Double> dailyPriceHistory;
    private HashMap<String, Double> weeklyPriceHistory;
    private HashMap<String, Double> monthlyPriceHistory;

    public HashMap<String, Double> getDailyPriceHistory() {
        return dailyPriceHistory;
    }

    public HashMap<String, Double> getWeeklyPriceHistory() {
        return weeklyPriceHistory;
    }

    public HashMap<String, Double> getMonthlyPriceHistory() {
        return monthlyPriceHistory;
    }
}
