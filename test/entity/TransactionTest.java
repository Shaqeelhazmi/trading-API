package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction;


    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock = new Stock("TSLA", priceHistory, "TESLA");
        transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Bought TESLA", 5, 10);
    }

    @Test
    void getTimestamp() {
        assert true;
    }

    @Test
    void getStock() {
        HashMap<String, Double> daily = new HashMap<String, Double>(5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock = new Stock("TSLA", priceHistory, "TESLA");
        assertEquals(stock.getStockName(), transaction.getStock());
    }

    @Test
    void getAction() {
        assertEquals("Bought TESLA", transaction.getAction());
    }

    @Test
    void getPricePerShare() {
        assertEquals(5, transaction.getPricePerShare());
    }

    @Test
    void getAmountOfShares() {
        assertEquals(10, transaction.getAmountOfShares());
    }
}