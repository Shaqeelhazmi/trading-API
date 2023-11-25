package use_case.buy;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyInputDataTest {

    BuyInputData buyInputData = new BuyInputData("TESLA", 5);


    @Test
    void getStockname() {
        assertEquals("TESLA", buyInputData.getStockname());
    }

    @Test
    void getAmount() {assertEquals(5, buyInputData.getAmount());}
}