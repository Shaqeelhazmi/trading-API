package data_access;

import api.AlphaVantage;
import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.buy.BuyDataAccessInterface;
import use_case.sell.SellDataAccessInterface;


import java.io.*;
import java.util.*;

public class StockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface {
    private final File jsonFile;
    private final JSONObject jsonObject;
    private final Map<String, Stock> stocks = new HashMap<>();

    public StockDataAccessObject(String jsonPath) throws IOException {
        this.jsonFile = new File(jsonPath);

        try (FileInputStream fileInputStream = new FileInputStream(jsonFile)) {
            String fileText = new String(fileInputStream.readAllBytes());
            if (fileText.isEmpty()) {
                this.jsonObject = new JSONObject();
            } else {
                this.jsonObject = new JSONObject(fileText);
            }
        }
        if (jsonObject.isEmpty()) {
            save();
        } else {
            for (String stockSymbol : jsonObject.keySet()) {
                JSONObject stockJsonObject = jsonObject.getJSONObject(stockSymbol);

                String name = stockJsonObject.getString("name");

                JSONObject priceHistoryJsonObject = stockJsonObject.getJSONObject("priceHistory");
                HashMap<String, Double> dailyPriceHistory = toStringDoubleMap(priceHistoryJsonObject.getJSONObject("dailyPriceHistory"));
                HashMap<String, Double> weeklyPriceHistory = toStringDoubleMap(priceHistoryJsonObject.getJSONObject("weeklyPriceHistory"));
                HashMap<String, Double> monthlyPriceHistory = toStringDoubleMap(priceHistoryJsonObject.getJSONObject("monthlyPriceHistory"));

                PriceHistory priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);

                Stock stock = new Stock(stockSymbol, name, priceHistory);
                stocks.put(stockSymbol, stock);
            }
        }
    }

    public void save(Stock stock) {
        JSONObject stockJsonObject = new JSONObject();

        String stockSymbol = stock.getStockSymbol();
        String name = stock.getStockName();

        JSONObject priceHistoryJsonObject = new JSONObject();
        PriceHistory priceHistory = stock.getPriceHistory();
        priceHistoryJsonObject.put("dailyPriceHistory", new JSONObject(priceHistory.getDailyPriceHistory()));
        priceHistoryJsonObject.put("weeklyPriceHistory", new JSONObject(priceHistory.getWeeklyPriceHistory()));
        priceHistoryJsonObject.put("monthlyPriceHistory", new JSONObject(priceHistory.getMonthlyPriceHistory()));

        stockJsonObject.put("stockSymbol", stockSymbol);
        stockJsonObject.put("name", name);
        stockJsonObject.put("priceHistory", priceHistoryJsonObject);

        stocks.put(stockSymbol, stock);
        jsonObject.put(stockSymbol, stockJsonObject);
        this.save();
    }

    private void save() {
        try {
            FileWriter file = new FileWriter(jsonFile);
            String jsonString = jsonObject.toString(4);
            file.write(jsonString);
            file.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByName(String identifier) {
        return stocks.containsKey(identifier);
    }

    public void addNewStock(String stockSymbol) {

        JSONObject responseJsonObjectName = new AlphaVantage("SYMBOL_SEARCH", stockSymbol).getJsonObject();
        JSONObject bestMatch = responseJsonObjectName.getJSONArray("bestMatches").getJSONObject(0);
        String name = bestMatch.getString("2. name");

        HashMap<String, Double> dailyPriceHistory = new HashMap<>();
        HashMap<String, Double> weeklyPriceHistory = new HashMap<>();
        HashMap<String, Double> monthlyPriceHistory = new HashMap<>();

        JSONObject responseJsonObjectDaily = new AlphaVantage("TIME_SERIES_DAILY", stockSymbol).getJsonObject();
        JSONObject responseJsonObjectWeekly = new AlphaVantage("TIME_SERIES_WEEKLY", stockSymbol).getJsonObject();
        JSONObject responseJsonObjectMonthly = new AlphaVantage("TIME_SERIES_MONTHLY", stockSymbol).getJsonObject();

        JSONObject timeSeriesDaily = responseJsonObjectDaily.getJSONObject("Time Series (Daily)");
        JSONObject timeSeriesWeekly = responseJsonObjectWeekly.getJSONObject("Weekly Time Series");
        JSONObject timeSeriesMonthly = responseJsonObjectMonthly.getJSONObject("Monthly Time Series");

        for (String date : timeSeriesDaily.keySet()) {
            dailyPriceHistory.put(date, timeSeriesDaily.getJSONObject(date).getDouble("1. open"));
        }
        for (String date : timeSeriesWeekly.keySet()) {
            weeklyPriceHistory.put(date, timeSeriesWeekly.getJSONObject(date).getDouble("1. open"));
        }
        for (String date : timeSeriesMonthly.keySet()) {
            monthlyPriceHistory.put(date, timeSeriesMonthly.getJSONObject(date).getDouble("1. open"));
        }

        PriceHistory priceHistory = new PriceHistory(dailyPriceHistory, weeklyPriceHistory, monthlyPriceHistory);
        Stock stock = new Stock(stockSymbol, name, priceHistory);
        this.save(stock);
    }

    public Stock getStock(String stockSymbol) {
        if (!this.existsByName(stockSymbol)) {
            this.addNewStock(stockSymbol);
        }
        return stocks.get(stockSymbol);
    }

    private static HashMap<String, Double> toStringDoubleMap(JSONObject jsonobj) throws JSONException {
        HashMap<String, Double> map = new HashMap<String, Double>();
        Iterator<String> keys = jsonobj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Double value = jsonobj.getDouble(key);
            map.put(key, value);
        }
        return map;
    }

    private static ArrayList<String> sortDateStrings(Set<String> keyset) {
        ArrayList<Integer> datesAsIntegers = new ArrayList<>();
        for (String date : keyset) {
            int intValueDate = Integer.parseInt(date.replace("-", ""));
            datesAsIntegers.add(intValueDate);
        }
        Collections.sort(datesAsIntegers);

        ArrayList<String> sortedDateStrings = new ArrayList<>();
        for (int intValueDate : datesAsIntegers) {
            StringBuilder tempStringBuilder = new StringBuilder(Integer.toString(intValueDate));
            tempStringBuilder.insert(4, "-");
            tempStringBuilder.insert(7, "-");

            String finalString = tempStringBuilder.toString();
            sortedDateStrings.add(finalString);
            if (sortedDateStrings.size() == 100) {break;}
        }

        return sortedDateStrings;
    }
}