package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private CommonUser user;
    private HashMap<String, Integer> stocksOwned;
    private Portfolio portfolio;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_TSLA = new Stock("TSLA", "Tesla Inc", priceHistory);
        Stock stock_AMZN = new Stock("AMZN", "Amazon.com Inc", priceHistory);
        List<String> favourites = new ArrayList<>(5);
        stocksOwned = new HashMap<>();
        stocksOwned.put(stock_TSLA.getStockSymbol(), 100);
        favourites.add(stock_TSLA.getStockSymbol());
        favourites.add(stock_AMZN.getStockSymbol());
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock_TSLA.getStockName(),
                "Sold Tesla Inc", 5.0, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock_AMZN.getStockName(),
                "Bought Amazon.com Inc", 10.0, 20);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        transactions.add(transaction2);
        portfolio = new Portfolio(stocksOwned, 10000);
        user = new CommonUser("John", "richboy", LocalDateTime.now(), favourites,
                portfolio, transactions);
    }

    @Test
    void getUsername() {
        assertEquals("John", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("richboy", user.getPassword());
    }

    @Test
    void getCreationTime() {
        assert true;
    }

    @Test
    void getPortfolio() {
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_TSLA = new Stock("TSLA", "Tesla Inc", priceHistory);
        stocksOwned = new HashMap<>();
        stocksOwned.put(stock_TSLA.getStockSymbol(), 100);
        Portfolio portfolio1 = new Portfolio(stocksOwned, 10000);
        assertEquals(portfolio1, user.getPortfolio());
    }

    @Test
    void setPortfolio() {
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_AAPL = new Stock("AAPL", "Apple Inc", priceHistory);
        stocksOwned.put(stock_AAPL.getStockSymbol(), 100);
        portfolio = new Portfolio(stocksOwned, 5000);
        user.setPortfolio(portfolio);
        assertEquals(portfolio, user.getPortfolio());

    }

    @Test
    void getFavourites() {
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_TSLA = new Stock("TSLA", "Tesla Inc", priceHistory);
        Stock stock_AMZN = new Stock("AMZN", "Amazon.com Inc", priceHistory);
        List<String> favourites = new ArrayList<>(5);
        favourites.add(stock_TSLA.getStockSymbol());
        favourites.add(stock_AMZN.getStockSymbol());
        assertEquals(user.getFavourites(), favourites);
    }


    @Test
    void setFavourites() {
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_AAPL = new Stock("AAPL", "Apple Inc", priceHistory);
        List<String> favourites = new ArrayList<>(5);
        favourites.add(stock_AAPL.getStockSymbol());
        user.setFavourites(favourites);
        assertEquals(user.getFavourites(), favourites);
    }

    @Test
    void getTransactionHistory() {
        HashMap<String, Double> daily_Price_History = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly_Price_History = new HashMap<>();
        HashMap<String, Double> monthly_Price_History = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily_Price_History, weekly_Price_History, monthly_Price_History);
        Stock stock_TSLA = new Stock("TSLA", "Tesla Inc", priceHistory);
        Stock stock_AMZN = new Stock("AMZN", "Amazon.com Inc", priceHistory);
        stocksOwned.put(stock_TSLA.getStockSymbol(), 100);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock_TSLA.getStockName(),
                "Sold Tesla Inc", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock_AMZN.getStockName(),
                "Bought Amazon.com Inc", 10, 20);
        ArrayList<Transaction> transaction= new ArrayList<>(5);
        transaction.add(transaction1);
        transaction.add(transaction2);
        assertEquals(transaction.get(0), user.getTransactionHistory().get(0));
    }
}