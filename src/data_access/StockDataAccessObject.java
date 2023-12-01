package data_access;

import entity.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class StockDataAccessObject {
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
            // iterate over every user in jsonObject
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


    /**
     * Return whether a user exists with username identifier.
     *
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
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

    public Stock getStock(String stockSymbol) {
        return stocks.get(stockSymbol);
    }

    public static HashMap<String, Double> toStringDoubleMap(JSONObject jsonobj) throws JSONException {
        HashMap<String, Double> map = new HashMap<String, Double>();
        Iterator<String> keys = jsonobj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Double value = jsonobj.getDouble(key);
            map.put(key, value);
        }
        return map;
    }
}