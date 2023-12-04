package data_access;

import entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

class StockDataAccessObjectTest {

    @Test
    void existsByName() {
        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sdao.existsByName("TSLA");
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

        StockDataAccessObject sdao;
        try {
            sdao = new StockDataAccessObject("./stocks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sdao.getStock("TSLA").getPriceHistory().getDailyPriceHistory().get("2023-11-18"));

        Set<String> dateStrings = sdao.getStock("TSLA").getPriceHistory().getDailyPriceHistory().keySet();

        ArrayList<Integer> datesAsIntegers = new ArrayList<>();
        for (String date : dateStrings) {
            int intValueDate = Integer.parseInt(date.replace("-", ""));
            datesAsIntegers.add(intValueDate);
        }
        Collections.sort(datesAsIntegers);
        Collections.reverse(datesAsIntegers);


        ArrayList<String> sortedDateStrings = new ArrayList<>();
        for (int intValueDate : datesAsIntegers) {
            StringBuilder tempStringBuilder = new StringBuilder(Integer.toString(intValueDate));
            tempStringBuilder.insert(4, "-");
            tempStringBuilder.insert(7, "-");

            String finalString = tempStringBuilder.toString();
            sortedDateStrings.add(finalString);
            System.out.println(finalString);
//            if (sortedDateStrings.size() == 100) {break;}
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