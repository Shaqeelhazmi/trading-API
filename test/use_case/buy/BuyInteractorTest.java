package use_case.buy;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryStockDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class BuyInteractorTest {

    CommonUser user;

    Stock stock1;

    Transaction transaction1;

    @BeforeEach
    void init(){
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 10.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        stock1 = new Stock("TSLA", "TESLA", priceHistory);
        

        ArrayList<String> favourites = new ArrayList<>(5);
        favourites.add(stock1.getStockName());
        HashMap<String, Integer> hashMap =  new HashMap<>();
        hashMap.put(stock1.getStockName(), 100);
        transaction1 = new Transaction(LocalDateTime.now(), stock1.getStockName(),
                "Bought TESLA", 5, 10);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        Portfolio portfolio = new Portfolio(hashMap, 10000);
        user = new CommonUser("test", "1234", LocalDateTime.now(), favourites, portfolio, transactions);

    }

    @Test
    void failureNotEnough() throws IOException {
        BuyInputData buyInputData = new BuyInputData(stock1.getStockSymbol(), 2500, user.getUsername());
        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./testUsers.json");
        userDataAccessObject.get("test").getPortfolio().setAccountBalance(10000);


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
        BuyInputBoundary interactor = new BuyInteractor(buyDataAccessInterface, failurePresenter, userDataAccessObject);
        interactor.buy(buyInputData);
    }

    @Test
    void successView() throws IOException {
        BuyInputData buyInputData = new BuyInputData(stock1.getStockSymbol(), 5, user.getUsername());
        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./testUsers.json");



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
        BuyInputBoundary iterator = new BuyInteractor(buyDataAccessInterface, successBuyPresenter,userDataAccessObject);
        iterator.buy(buyInputData);
    }

}