package use_case.sell;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.buy.BuyOutputData;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;

class SellOutputDataTest {

    HashMap<String, Double> daily = new HashMap<>(5);

    HashMap<String, Double> weekly = new HashMap<>();

    HashMap<String, Double> monthly = new HashMap<>();

    PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);

    Stock stock = new Stock("TSLA",  priceHistory, "TESLA");

    SellOutputData sellOutputData;
    @BeforeEach
    void setUp() {
        sellOutputData = new SellOutputData(stock.getStockName(), LocalDateTime.now().toString());
    }


    @Test
    void getStockSold() {
        assertEquals("TESLA", sellOutputData.getStockSold());
    }

    @Test
    void setCreationTime() {
        assert true;
    }

    @Test
    void getCreationTime() {
        assertNotNull(sellOutputData.getCreationTime());
    }
}