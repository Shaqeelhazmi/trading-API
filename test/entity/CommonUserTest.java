package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser user;

    private HashMap<String, Integer> hashMap;
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
        hashMap = new HashMap<>();
        hashMap.put(stock1.getStockSymbol(), 100);
        favourites.add(stock1);
        favourites.add(stock2);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Sold AMAZON", 10, 20);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        transactions.add(transaction2);
        portfolio = new Portfolio(hashMap, 10000);
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
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        hashMap = new HashMap<>();
        hashMap.put(stock1.getStockSymbol(), 100);
        assertEquals(hashMap, user.getPortfolio().getPortfolio());
    }

    @Test
    void setPortfolio() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock2 = new Stock("AAPL", priceHistory, "APPLE");
        hashMap.put(stock2.getStockSymbol(), 100);
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
        assertEquals(user.getFavourites().get(0).getStockName(), favourites.get(0).getStockName());
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
        hashMap.put(stock1.getStockSymbol(), 100);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Sold AMAZON", 10, 20);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        transactions.add(transaction2);
        assertEquals(transactions.get(0), user.getTransactionHistory().get(0));
    }
}