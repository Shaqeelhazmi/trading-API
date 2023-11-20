package data_access;

import entity.*;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import use_case.buy.BuyDataAccessInterface;
import use_case.searching.SearchDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class StockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface, SearchDataAccessInterface {
    private final JSObject jsonFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Stock> stocks = new HashMap<>();

    private StockFactory stockFactory;

    public StockDataAccessObject(String JsonPath, StockFactory stockFactory) throws IOException {
        this.stockFactory = stockFactory;

        jsonFile = new JSObject() {
            @Override
            public Object call(String methodName, Object... args) throws JSException {
                return null;
            }

            @Override
            public Object eval(String s) throws JSException {
                return null;
            }

            @Override
            public Object getMember(String name) throws JSException {
                return null;
            }

            @Override
            public void setMember(String name, Object value) throws JSException {

            }

            @Override
            public void removeMember(String name) throws JSException {

            }

            @Override
            public Object getSlot(int index) throws JSException {
                return null;
            }

            @Override
            public void setSlot(int index, Object value) throws JSException {

            }
        };
        //change
        headers.put("stockSymbol", 0);
        headers.put("priceHistory", 1);

        if (jsonFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
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

    public StockDataAccessObject(File jsonFile) {
        this.jsonFile = jsonFile;
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
            writer = new BufferedWriter(new FileWriter(jsonFile));
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

    public void buy(int amount, Stock stock, CommonUser user){
        // Change the amount the user have of that stock in portfolio
        user.getPortfolio().getPortfolio().put(stock, user.getPortfolio().getPortfolio().get(stock) + amount);

        // Get the amount of money you have in portfolio
        double current_balance_portfolio = user.getPortfolio().getAccountBalance();

        // Get the total price of the stock you are buying
        double amount_used_for_purchase = stock.getPriceHistory().getDailyPriceHistory().get(stock.getStockSymbol()) * amount;

        // Updating the amount left in account
        user.getPortfolio().setAccountBalance(current_balance_portfolio - amount_used_for_purchase);

        //Updating transaction history
        double pricePerShare = stock.getPriceHistory().getDailyPriceHistory().get(stock.getStockSymbol());
        Transaction transaction = new Transaction(LocalDateTime.now(), stock, "Bought" + stock.getStockName(),
                pricePerShare, amount);
        user.getTransactionHistory().getPurchaseHistory().add(transaction);
    }

    public void search(String stockName) {

    }

    public void sell(Portfolio portfolio, int amount, Stock stock){

        portfolio.getPortfolio().put(stock, portfolio.getPortfolio().get(stock) - amount);

        double current_balance_portfolio = portfolio.getAccountBalance();

        double amount_received = stock.getPriceHistory().getDailyPriceHistory().get(stock.getStockSymbol()) * amount;

        portfolio.setAccountBalance(current_balance_portfolio + amount_received);
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
