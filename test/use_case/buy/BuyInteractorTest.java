package use_case.buy;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuyInteractorTest {

    HashMap<String, Double> daily = new HashMap<String, Double>(5);

    HashMap<String, Double> weekly = new HashMap<String, Double>();

    HashMap<String, Double> monthly = new HashMap<String, Double>();

    PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);


    Stock stock = new Stock("TSLA",  priceHistory, "TESLA");


    @Test
    void prepareSuccessViewTest(){
        BuyInputData inputData = new BuyInputData(stock, 5);
        BuyOutputBoundary successPresenter = new BuyOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Use case failure unexpected");
            }

            @Override
            public void prepareSuccessView(BuyOutputData buyOutputData) {
                assertEquals(inputData.getStockname(), buyOutputData.getStockBought());
                assertEquals(inputData.getAmount(), buyOutputData.getAmountStock());
            }
        };

    }

    @Test
    void prepareNotAvailableTest() {

    }


    @Test
    void prepareNotEnoughTest(){

    }

    @Test
    void amount_used_for_purchase() {
    }

    @Test
    void afford() {
    }
}