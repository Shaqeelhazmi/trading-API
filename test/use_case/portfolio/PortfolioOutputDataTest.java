package use_case.portfolio;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

class PortfolioOutputDataTest {
    CommonUser user;

    Stock stock1;

    Stock stock2;

    Transaction transaction1;

    Transaction transaction2;
    PortfolioOutputData portfolioOutputData;
    @BeforeEach
    void setUp() {
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 4.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        stock1 = new Stock("TSLA", "Tesla Inc", priceHistory);
        stock2 = new Stock("GOOGL", "Alphabet Inc - Class A", priceHistory);

        HashMap<String, Integer> stocksOwned =  new HashMap<>();
        stocksOwned.put(stock1.getStockSymbol(), 99);
        stocksOwned.put(stock2.getStockSymbol(), 12);
        Portfolio portfolio = new Portfolio(stocksOwned, 9999);

        portfolioOutputData = new PortfolioOutputData(portfolio.getPortfolio(), portfolio.getAccountBalance());
    }
    @Test
    void getStocksOwned() {
        assertEquals("{GOOGL=12, TSLA=99}",portfolioOutputData.getStocksOwned().toString());
    }

    @Test
    void getAccountBalance() {
        assertEquals(9999, portfolioOutputData.getAccountBalance());
    }
}