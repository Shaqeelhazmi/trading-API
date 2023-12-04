package use_case.sell;

import data_access.FileUserDataAccessObject;
import data_access.StockDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SellInteractorTest {

    CommonUser user;

    Stock stock1;

    Stock stock2;

   Transaction transaction1;

    Transaction transaction2;
    @BeforeEach
    void setUp() {
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 4.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        stock1 = new Stock("TSLA", "Tesla Inc", priceHistory);
        stock2 = new Stock("GOOGL", "Alphabet Inc - Class A", priceHistory);
        ArrayList<String> favourites = new ArrayList<>(5);
        favourites.add(stock1.getStockName());
        favourites.add(stock2.getStockName());
        HashMap<String, Integer> hashMap =  new HashMap<>();
        hashMap.put(stock1.getStockSymbol(), 99);
        transaction1 = new Transaction(LocalDateTime.now(), stock1.getStockName(),
                "Sold Tesla Inc", 5, 10);
        transaction2 = new Transaction(LocalDateTime.now(), stock2.getStockName(),
                "Sold Alphabet Inc - Class A", 10, 20);
        ArrayList<Transaction> transactions= new ArrayList<>(5);
        transactions.add(transaction1);
        transactions.add(transaction2);
        Portfolio portfolio = new Portfolio(hashMap, 9999);
        user = new CommonUser("test", "1234", LocalDateTime.now(), favourites, portfolio, transactions);
    }

    @Test
    void successView() throws IOException {
        SellInputData sellInputData = new SellInputData(stock1.getStockSymbol(), 1, user.getUsername());
        SellDataAccessInterface sellDataAccessInterface = new StockDataAccessObject("./stocks.json");
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./testUsers.json");

        SellOutputBoundary successSellPresenter = new SellOutputBoundary() {
            @Override
            public void prepareSuccessView(SellOutputData sellOutputData) {
                assertNotNull(sellOutputData.getCreationTime());
                assertEquals("Tesla Inc", sellOutputData.getStockSold());
            }
            @Override
            public void prepareFailView(String message) {
                fail("Use Case failure is unexpected");
            }

        };
        SellInputBoundary interactor = new SellInteractor(sellDataAccessInterface, successSellPresenter, userDataAccessObject);
        interactor.sell(sellInputData);
    }

    @Test
    void failView() throws IOException {
        SellInputData sellInputData = new SellInputData(stock1.getStockSymbol(), 500, user.getUsername());
        SellDataAccessInterface sellDataAccessInterface = new StockDataAccessObject("./stocks.json");
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./testUsers.json");

        SellOutputBoundary failurePresenter = new SellOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                assertEquals("You do not own enough of the stock to sell this amount.", message);
            }

            @Override
            public void prepareSuccessView(SellOutputData sellOutputData) {
                fail("Use Case success is unexpected");
            }
        };
        SellInputBoundary interactor = new SellInteractor(sellDataAccessInterface, failurePresenter, userDataAccessObject);
        interactor.sell(sellInputData);
    }
}