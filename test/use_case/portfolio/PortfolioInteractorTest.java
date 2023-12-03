package use_case.portfolio;

import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioInteractorTest {
    CommonUser user;

    Stock stock1;

    Stock stock2;

    Transaction transaction1;

    Transaction transaction2;
    @BeforeEach
    void setUp() {
//        HashMap<String, Double> daily = new HashMap<>(5);
//        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 4.5);
//        HashMap<String, Double> weekly = new HashMap<>();
//        HashMap<String, Double> monthly = new HashMap<>();
//        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
//        stock1 = new Stock("TSLA", "Tesla Inc", priceHistory);
//        stock2 = new Stock("GOOGL", "Alphabet Inc - Class A", priceHistory);
//        ArrayList<String> favourites = new ArrayList<>(5);
//        favourites.add(stock1.getStockName());
//        favourites.add(stock2.getStockName());
//        HashMap<String, Integer> stocksOwned =  new HashMap<>();
//        stocksOwned.put(stock1.getStockSymbol(), 99);
//        stocksOwned.put(stock2.getStockSymbol(), 12);
//        transaction1 = new Transaction(LocalDateTime.now(), stock1.getStockName(),
//                "Sell", 5, 10);
//        transaction2 = new Transaction(LocalDateTime.now(), stock2.getStockName(),
//                "Sell", 10, 20);
//        ArrayList<Transaction> transactions= new ArrayList<>(5);
//        transactions.add(transaction1);
//        transactions.add(transaction2);
//        Portfolio portfolio = new Portfolio(stocksOwned, 9999);
//        user = new CommonUser("test", "1234", LocalDateTime.now(), favourites, portfolio, transactions);
    }

    @Test
    void execute() throws IOException {
        PortfolioInputData portfolioInputData = new PortfolioInputData("tom");
        PortfolioDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./testUsers.json");

        double errorTolerance = 0.001;
        PortfolioOutputBoundary successPresenter = new PortfolioOutputBoundary() {
            @Override
            public void prepareSuccessView(PortfolioOutputData portfolioOutputData) {
                assertTrue(Double.valueOf(Math.abs(portfolioOutputData.getAccountBalance() - 5411.16)) < errorTolerance);
                assertEquals("{GOOGL=6, TSLA=12}" ,portfolioOutputData.getStocksOwned().toString());
            }

        };

        PortfolioInputBoundary interactor = new PortfolioInteractor(userDataAccessObject, successPresenter);
        interactor.execute(portfolioInputData);
    }
}