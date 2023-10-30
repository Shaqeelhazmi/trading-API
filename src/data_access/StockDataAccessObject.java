package data_access;

import entity.PriceHistory;
import entity.Stock;
import entity.StockFactory;
import use_case.buy.BuyDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class StockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Stock> stocks = new HashMap<>();

    private StockFactory stockFactory;

    public StockDataAccessObject(String csvPath, StockFactory stockFactory) throws IOException {
        this.stockFactory = stockFactory;

        csvFile = new File(csvPath);
        //change
        headers.put("stockSymbol", 0);
        headers.put("priceHistory", 1);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String stockSymbol = String.valueOf(col[headers.get("username")]);
                    PriceHistory priceHistory = None; //change!
                    Stock stock = stockFactory.create(stockSymbol, priceHistory);
                    stocks.put(stockSymbol, stock);
                }
            }
        }
    }

    public StockDataAccessObject(File csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public void save(Stock stock) {
        stocks.put(stock.getStockSymbol(), stock);
        this.save();
    }

    @Override
    public Stock get(String stockSymbol) {
        return stocks.get(stockSymbol);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Stock stock : stocks.values()) {
                String line = String.format("%s,%s",
                        stock.getStockSymbol(), stock.getPriceHistory());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return stocks.containsKey(identifier);
    }
}