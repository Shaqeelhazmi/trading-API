package data_access;

import entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    void addNewStock() {
        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sdao.addNewStock("TSLA");
    }
    @Test
    void sandbox() {
        // this was made just to play around with how java works
        String date = "2023-07-31";
        String date2 = "2023-04-31";
        Integer dateInt = Integer.parseInt(date.replace("-", ""));
        Integer dateInt2 = Integer.parseInt(date2.replace("-", ""));
        ArrayList<Integer> ints = new ArrayList<>();

        ints.add(dateInt);
        ints.add(dateInt2);

        Collections.sort(ints);
        System.out.println(ints);

        ArrayList<Integer> datesAsIntegers = new ArrayList<>();
        datesAsIntegers.add(20230431);


        for (int intValueDate : datesAsIntegers) {
            StringBuilder tempStringBuilder = new StringBuilder(Integer.toString(intValueDate));
            tempStringBuilder.insert(4, "-");
            tempStringBuilder.insert(7, "-");
            String finalString = tempStringBuilder.toString();
            System.out.println(finalString);
        }
    }

    @Test
    void updateStockDatabase() {
        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sdao.updateStockDatabase();
    }
}