package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioInterfaceTest {

    private HashMap<Stock, Integer> hashMap;

    private Portfolio portfolio;


    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock = new Stock("TSLA", priceHistory, "TESLA");
        hashMap.put(stock, 100);
        portfolio = new Portfolio(hashMap, 10000);

    }
    @Test
    void getPortfolio() {
        assertEquals(hashMap, portfolio.getPortfolio());
    }

    @Test
    void getAccountBalance(){
        assertEquals(10000, portfolio.getAccountBalance());
    }

    @Test
    void setAccountBalance(){
        double new_account_balance = 5000;
        portfolio.setAccountBalance(new_account_balance);
        assertEquals(new_account_balance, portfolio.getAccountBalance());
    }
}