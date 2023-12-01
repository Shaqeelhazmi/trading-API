package data_access;

import entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

class StockDataAccessObjectTest {

    @Test
    void existsByName() {
    }
    @Test
    void save() {
        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();
        dailyPriceHistory.put("2023-11-30", 156.95);
        weeklyPriceHistory.put("2023-11-30", 156.95);
        monthlyPriceHistory.put("2023-11-30", 156.95);


        PriceHistory testPriceHistory = new PriceHistory(
                dailyPriceHistory,
                weeklyPriceHistory,
                monthlyPriceHistory
        );
        Stock testStock = new Stock("TSLA", "Tesla", testPriceHistory);
        sdao.save(testStock);
    }
    @Test
    void getStock() {
        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stock stock = sdao.getStock("TSLA");
        System.out.println(stock.getStockName());
        System.out.println(stock.getStockSymbol());
        System.out.println(stock.getPriceHistory());
    }
}