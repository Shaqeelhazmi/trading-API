package data_access;

import entity.CommonUser;
import entity.PriceHistory;
import entity.Stock;
import entity.Transaction;
import use_case.buy.BuyDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface {

    private  Map<String, Stock> stocks = new HashMap<>();

    public InMemoryStockDataAccessObject(){
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 10.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", "Tesla Inc", priceHistory);
        stocks.put(stock1.getStockSymbol(), stock1);
    }


    @Override
    public boolean existsByName(String identifier) {
        return stocks.containsKey(identifier);
    }

    @Override
    public Stock getStockObject(String stockSymbol) {
//        Cannot extra a stock using a stockSymbol hence using a dummy data

        return stocks.get(stockSymbol);
    }

}



//    @Override
//    public void sell(int amount, Stock stock, CommonUser user, double amount_received) {
//        // Change the amount the user have of that stock in portfolio
//        if (user.getPortfolio().getPortfolio().get(stock.getStockSymbol()) == amount) {
//            user.getPortfolio().getPortfolio().remove(stock.getStockSymbol(), amount);
//        } else {
//            int old_value = user.getPortfolio().getPortfolio().get(stock.getStockSymbol());
//            int new_value = old_value - amount;
//            user.getPortfolio().getPortfolio().replace(stock.getStockSymbol(),
//                    old_value, new_value);
//        }
//
//        // Get the amount of money you have in portfolio
//        double current_balance_portfolio = user.getPortfolio().getAccountBalance();
//
//        // Updating the amount left in account
//        user.getPortfolio().setAccountBalance(current_balance_portfolio + amount_received);
//
//        Transaction transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Sell",
//                (amount_received/amount), amount);
//        user.getTransactionHistory().add(transaction);
//    }

