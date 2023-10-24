package entity;

import java.util.HashMap;

public interface PriceHistoryInterface {
    public HashMap<String, Double> getDailyPriceHistory();
    public HashMap<String, Double> getWeeklyPriceHistory();
    public HashMap<String, Double> getMonthlyPriceHistory();
}
