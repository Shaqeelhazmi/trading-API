package use_case.buy;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyInputDataTest {
    HashMap<String, Double> daily = new HashMap<String, Double>(5);

    HashMap<String, Double> weekly = new HashMap<String, Double>();

    HashMap<String, Double> monthly = new HashMap<String, Double>();

    PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);

    Stock stock = new Stock("TSLA",  priceHistory, "TESLA");
    BuyInputData buyInputData = new BuyInputData(stock, 5);

    @Test
    void getStockname() {
        assertEquals(buyInputData.getStockname(), stock.getStockName());
    }

    @Test
    void getAmount() {
    }
}