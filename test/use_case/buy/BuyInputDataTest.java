package use_case.buy;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BuyInputDataTest {

    BuyInputData buyInputData = new BuyInputData("TSLA", 5, "Bob");


    @Test
    void getStockSymbol() {
        assertEquals("TSLA", buyInputData.getStockSymbol());
    }

    @Test
    void getAmount() {assertEquals(5, buyInputData.getAmount());}

    @Test
    void getUserName() {assertEquals("Bob", buyInputData.getUserName()); }
}