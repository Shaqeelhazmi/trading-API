package data_access;

import entity.CommonUser;
import entity.Stock;
import entity.Transaction;
import use_case.buy.BuyDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryStockDataAccessObject implements BuyDataAccessInterface {

    private final Map<String, Stock> stocks = new HashMap<>();

    @Override
    public boolean existsByName(String identifier) {
        return stocks.containsKey(identifier);
    }

    @Override
    public void buy(int amount, Stock stock, CommonUser user) {
        // Change the amount the user have of that stock in portfolio
        user.getPortfolio().getPortfolio().put(stock.getStockSymbol(), user.getPortfolio().getPortfolio().get(stock.getStockSymbol()) + amount);

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
}
