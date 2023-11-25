package use_case.buy;


import org.junit.jupiter.api.Test;


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