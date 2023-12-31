package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioInterfaceTest {
    private HashMap<String, Integer> stocksOwned;

    private Portfolio portfolio;

    @BeforeEach
    void init(){
        stocksOwned = new HashMap<>();
        stocksOwned.put("Tesla Inc", 100);
        portfolio = new Portfolio(stocksOwned, 10000);
    }

    @Test
    void getPortfolio() {
        HashMap<String, Integer> stocksOwned2 = new HashMap<>();
        stocksOwned2.put("Tesla Inc", 100);
        assertEquals(stocksOwned2 ,portfolio.getPortfolio());
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