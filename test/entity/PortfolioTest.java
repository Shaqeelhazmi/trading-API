package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioTest {
    private HashMap<String, Integer> stocksOwned;

    private Portfolio portfolio;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock = new Stock("TSLA", "Tesla Inc", priceHistory);
        stocksOwned = new HashMap<String, Integer>();
        stocksOwned.put(stock.getStockName(), 100);
        portfolio = new Portfolio(stocksOwned, 10000);

    }
    @Test
    void getPortfolio() {
        HashMap<String, Integer> stocksOwned2 = new HashMap<>();
        stocksOwned2.put("Tesla Inc", 100);
        assertEquals(stocksOwned2, portfolio.getPortfolio());
    }

    @Test
    void getAccountBalance(){
        assertEquals(10000, portfolio.getAccountBalance());
    }

    @Test
    void setAccountBalance(){
        double new_account_balance = 5000;
        portfolio.setAccountBalance(new_account_balance);
        assertEquals(5000, portfolio.getAccountBalance());
    }
}
