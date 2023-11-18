package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser user;

    private HashMap<Stock, Integer> hashMap;
    private Portfolio portfolio;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        Stock stock2 = new Stock("AMZN", priceHistory, "AMAZON");
        ArrayList<Stock> favourites = new ArrayList<>(5);
        favourites.add(stock1);
        favourites.add(stock2);
        hashMap.put(stock1, 100);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Sold AMAZON", 10, 20);
        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
        purchaseHistory.add(transaction1);
        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
        sellHistory.add(transaction2);
        TransactionHistory transactionHistory = new TransactionHistory(purchaseHistory, sellHistory);
        portfolio = new Portfolio(hashMap, 10000);
        user = new CommonUser("John", "richboy", LocalDateTime.now(), favourites,
                portfolio, transactionHistory);
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
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        hashMap.put(stock1, 100);
        portfolio = new Portfolio(hashMap, 10000);
        assertEquals(portfolio, user.getPortfolio());
    }

    @Test
    void setPortfolio() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock2 = new Stock("AAPL", priceHistory, "APPLE");
        hashMap.put(stock2, 100);
        portfolio = new Portfolio(hashMap, 5000);
        user.setPortfolio(portfolio);
        assertEquals(portfolio, user.getPortfolio());

    }

    @Test
    void getFavourites() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        Stock stock2 = new Stock("AMZN", priceHistory, "AMAZON");
        ArrayList<Stock> favourites = new ArrayList<>(5);
        favourites.add(stock1);
        favourites.add(stock2);
        assertEquals(favourites, user.getFavourites());
    }

    @Test
    void setFavourites() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("AAPL", priceHistory, "APPLE");
        ArrayList<Stock> favourites = new ArrayList<>(5);
        favourites.add(stock1);
        user.setFavourites(favourites);
        assertEquals(favourites, user.getFavourites());
    }

    @Test
    void getTransactionHistory() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        Stock stock2 = new Stock("AMZN", priceHistory, "AMAZON");
        ArrayList<Stock> favourites = new ArrayList<>(5);
        favourites.add(stock1);
        favourites.add(stock2);
        hashMap.put(stock1, 100);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Sold AMAZON", 10, 20);
        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
        purchaseHistory.add(transaction1);
        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
        sellHistory.add(transaction2);
        TransactionHistory transactionHistory = new TransactionHistory(purchaseHistory, sellHistory);
        assertEquals(transactionHistory, user.getTransactionHistory());
    }

    @Test
    void setTransactionHistory() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("AAPL", priceHistory, "APPLE");
        Stock stock2 = new Stock("MSFT", priceHistory, "MICROSOFT");
        ArrayList<Stock> favourites = new ArrayList<>(5);
        favourites.add(stock1);
        favourites.add(stock2);
        hashMap.put(stock1, 100);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought APPLE", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Sold MICROSOFT", 10, 20);
        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
        purchaseHistory.add(transaction1);
        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
        sellHistory.add(transaction2);
        TransactionHistory transactionHistory = new TransactionHistory(purchaseHistory, sellHistory);
        user.setTransactionHistory(transactionHistory);
        assertEquals(transactionHistory, user.getTransactionHistory());
    }
}