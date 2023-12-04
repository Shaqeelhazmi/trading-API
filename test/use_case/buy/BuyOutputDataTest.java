package use_case.buy;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyOutputDataTest {

    HashMap<String, Double> daily = new HashMap<String, Double>(5);

    HashMap<String, Double> weekly = new HashMap<String, Double>();

    HashMap<String, Double> monthly = new HashMap<String, Double>();

    PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);

    Stock stock = new Stock("TSLA", "TESLA", priceHistory);

    BuyOutputData buyOutputData;

    @BeforeEach
    void init(){
        buyOutputData = new BuyOutputData(stock.getStockName(), LocalDateTime.now().toString(), 5, 10000);
    }

    @Test
    void setCreationTime() {
        buyOutputData.setCreationTime(LocalDateTime.now().toString());
        assertNotNull(buyOutputData.getCreationTime());
    }

    @Test
    void getCreationTime() {
        assertNotNull(buyOutputData.getCreationTime());
    }

    @Test
    void getStockBought() {
        assertEquals("TESLA", buyOutputData.getStockBought());
    }
}