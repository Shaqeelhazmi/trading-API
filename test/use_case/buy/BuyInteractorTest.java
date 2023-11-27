package use_case.buy;

import data_access.InMemoryStockDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BuyInteractorTest {

    CommonUser user;

    Stock stock1;

    Stock stock2;

    Transaction transaction1;

    Transaction transaction2;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 10.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        stock1 = new Stock("TSLA", priceHistory, "TESLA");
        stock2 = new Stock("AMXN", priceHistory, "AMAXON");
        ArrayList<String> favourites = new ArrayList<>(5);
        favourites.add(stock1.getStockName());
        favourites.add(stock2.getStockName());
        HashMap<String, Integer> hashMap =  new HashMap<>();
        hashMap.put(stock1.getStockName(), 100);
        transaction1 = new Transaction(LocalDateTime.now(), stock1.getStockName(),
                "Bought TESLA", 5, 10);
        transaction2 = new Transaction(LocalDateTime.now(), stock2.getStockName(),
                "Sold AMAXON", 10, 20);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        transactions.add(transaction2);
        Portfolio portfolio = new Portfolio(hashMap, 10000);
        user = new CommonUser("John", "richboy", LocalDateTime.now(), favourites, portfolio, transactions);

    }

    @Test
    void successView() {
        BuyInputData buyInputData = new BuyInputData(stock1.getStockName(), 5);
        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();

        BuyOutputBoundary successBuyPresenter = new BuyOutputBoundary() {
            @Override
            public void prepareSuccessView(BuyOutputData buyOutputData) {
                assertNotNull(buyOutputData.getCreationTime());
                assertEquals("TESLA",buyOutputData.getStockBought());
            }
            @Override
            public void prepareFailView(String message) {
                fail("Use Case failure is unexpected");
            }

        };
        BuyInputBoundary iterator = new BuyInteractor(buyDataAccessInterface, user, successBuyPresenter, stock1);
        iterator.buy(buyInputData);
    }

    @Test
    void failureNotEnough(){
        BuyInputData buyInputData = new BuyInputData(stock1.getStockName(), 2500);
        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();

        BuyOutputBoundary failurePresenter = new BuyOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertEquals("You do not have enough non-liquid balance to make this purchase, you " +
                        "can only afford " + 952 + " stocks", message);
            }

            @Override
            public void prepareSuccessView(BuyOutputData buyOutputData) {
                fail("Use Case success is unexpected");
            }
        };
        BuyInputBoundary interactor = new BuyInteractor(buyDataAccessInterface, user, failurePresenter, stock1);
        interactor.buy(buyInputData);
    }
}