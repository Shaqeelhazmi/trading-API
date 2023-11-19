package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryInterfaceTest {

    private TransactionHistory transactionHistory;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        Stock stock2 = new Stock("AMZN", priceHistory, "AMAZON");
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Bought AMAZON", 10, 20);
        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
        purchaseHistory.add(transaction1);
        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
        sellHistory.add(transaction2);
        transactionHistory = new TransactionHistory(purchaseHistory, sellHistory);
    }

    @Test
    void getPurchaseHistory() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        Transaction transaction1 = new Transaction(LocalDateTime.now(), stock1,
                "Bought TESLA", 5, 10);
        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
        purchaseHistory.add(transaction1);
        assertEquals(purchaseHistory, transactionHistory.getPurchaseHistory());
    }

    @Test
    void getSellHistory() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock2 = new Stock("AMZN", priceHistory, "AMAZON");
        Transaction transaction2 = new Transaction(LocalDateTime.now(), stock2,
                "Bought AMAZON", 10, 20);
        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
        sellHistory.add(transaction2);
        assertEquals(sellHistory, transactionHistory.getPurchaseHistory());
    }
}