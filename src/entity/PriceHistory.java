package entity;

import java.util.HashMap;

public class PriceHistory implements PriceHistoryInterface {
    private HashMap<String, Double> dailyPriceHistory;
    private HashMap<String, Double> weeklyPriceHistory;
    private HashMap<String, Double> monthlyPriceHistory;

    public PriceHistory(HashMap<String, Double> dailyPriceHistory, HashMap<String, Double> weeklyPriceHistory, HashMap<String, Double> monthlyPriceHistory){
        this.dailyPriceHistory = dailyPriceHistory;
        this.weeklyPriceHistory = weeklyPriceHistory;
        this.monthlyPriceHistory = monthlyPriceHistory;
    }

    @Override
    public HashMap<String, Double> getDailyPriceHistory() {
        return dailyPriceHistory;
    }

    @Override
    public HashMap<String, Double> getWeeklyPriceHistory() {
        return weeklyPriceHistory;
    }

    @Override
    public HashMap<String, Double> getMonthlyPriceHistory() {
        return monthlyPriceHistory;
    }
}
