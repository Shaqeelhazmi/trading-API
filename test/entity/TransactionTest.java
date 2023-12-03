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
        transaction = new Transaction(LocalDateTime.now(), "Tesla Inc", "Bought Tesla Inc", 5, 10);
    }

    @Test
    void getTimestamp() {
        assert true;
    }

    @Test
    void getStock() {
        assertEquals("Tesla Inc", transaction.getStock());
    }

    @Test
    void getAction() {
        assertEquals("Bought Tesla Inc", transaction.getAction());
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