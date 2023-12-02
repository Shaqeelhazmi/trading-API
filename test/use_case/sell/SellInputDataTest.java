package use_case.sell;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.buy.BuyInputData;

import static org.junit.jupiter.api.Assertions.*;

class SellInputDataTest {

    SellInputData sellInputData = new SellInputData("TSLA", 10, "bill");

    @Test
    void getStockSymbol() {
        assertEquals("TSLA", sellInputData.getStockSymbol());
    }

    @Test
    void getAmount() {
        assertEquals(10, sellInputData.getAmount());
    }
    @Test
    void getUserName() {assertEquals("bill", sellInputData.getUserName()); }
}