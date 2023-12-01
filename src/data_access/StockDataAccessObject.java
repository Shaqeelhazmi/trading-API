package data_access;

import entity.*;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.buy.BuyDataAccessInterface;
import use_case.searching.SearchDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class StockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface, SearchDataAccessInterface {
    private final File jsonFile;

    private final Map<String, Stock> stocks = new HashMap<>();

    private StockFactory stockFactory;

    public StockDataAccessObject(String JsonPath, StockFactory stockFactory) throws IOException {
        this.jsonFile = new File(jsonPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
            String jsonString = jsonStringBuilder.toString();
            if (jsonString.isEmpty()) {
                this.jsonObject = new JSONObject();
            } else {
                this.jsonObject = new JSONObject(jsonString);
            }
        }
        if (jsonObject.isEmpty()) {
            save();
        } else {
            // iterate over every user in jsonObject
            for (String username : jsonObject.keySet()) {
                JSONObject account = jsonObject.getJSONObject(username);
                String accountUsername = account.getString("username");
                String accountPassword = account.getString("password");
                LocalDateTime creationTime = LocalDateTime.parse(account.getString("creationTime"));
                List<String> favourites = toStringList(account.getJSONArray("favourites"));

                JSONObject portfolioJsonObject = account.getJSONObject("portfolio");
                HashMap<String, Integer> portfolioMap = toStringIntMap(portfolioJsonObject.getJSONObject("portfolio"));
                double accountBalance = portfolioJsonObject.getDouble("accountBalance");
                Portfolio portfolio = new Portfolio(portfolioMap, accountBalance);

                ArrayList<Transaction> transactionHistory = new ArrayList<>();
                JSONArray transactionHistoryJsonArray = account.getJSONArray("transactionHistory");
                for (int i = 0; i < transactionHistoryJsonArray.length(); i++) {
                    JSONObject transactionJsonObject = transactionHistoryJsonArray.getJSONObject(i);
                    LocalDateTime timestamp = LocalDateTime.parse(transactionJsonObject.getString("timestamp"));
                    String stock = transactionJsonObject.getString("stock");
                    String action = transactionJsonObject.getString("action");
                    double pricePerShare = transactionJsonObject.getDouble("pricePerShare");
                    int amountOfShares = transactionJsonObject.getInt("amountOfShares");
                    transactionHistory.add(new Transaction(timestamp, stock, action, pricePerShare, amountOfShares));
                }

                CommonUser user = new CommonUser(accountUsername, accountPassword, creationTime, favourites, portfolio, transactionHistory);
                accounts.put(username, user);

            }

        }
    }

    public void buy(int amount, Stock stock, CommonUser user, double amount_used_for_purchase){
        if (user.getPortfolio().getPortfolio().containsKey(stock.getStockSymbol())) {
           int old_value = user.getPortfolio().getPortfolio().get(stock.getStockSymbol());
           int new_value = old_value + amount;
           user.getPortfolio().getPortfolio().replace(stock.getStockSymbol(), old_value, new_value);
           } else {
             user.getPortfolio().getPortfolio().put(stock.getStockName(), amount);
           }

           // Get the amount of money you have in portfolio
           double current_balance_portfolio = user.getPortfolio().getAccountBalance();

           // Updating the amount left in account
           user.getPortfolio().setAccountBalance(current_balance_portfolio - amount_used_for_purchase);

           //Updating transaction history
           Transaction transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Buy",
           (amount_used_for_purchase/amount), amount);
           user.getTransactionHistory().add(transaction);
           }
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
