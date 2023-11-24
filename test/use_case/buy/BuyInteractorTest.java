//package use_case.buy;
//
//import data_access.InMemoryStockDataAccessObject;
//import data_access.StockDataAccessObject;
//import entity.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BuyInteractorTest {
//
//    CommonUser user;
//
//    Stock stock1;
//
//    Stock stock2;
//
//    Transaction transaction1;
//
//    Transaction transaction2;
//
//    @BeforeEach
//    void init(){
//        HashMap<String, Double> daily = new HashMap<String, Double>(5);
//        HashMap<String, Double> weekly = new HashMap<>();
//        HashMap<String, Double> monthly = new HashMap<>();
//        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
//        stock1 = new Stock("TSLA", priceHistory, "TESLA");
//        stock2 = new Stock("AMXN", priceHistory, "AMAXON");
//        ArrayList<Stock> favourites = new ArrayList<>(5);
//        favourites.add(stock1);
//        favourites.add(stock2);
//        HashMap<Stock, Integer> hashMap =  new HashMap<>();
//        hashMap.put(stock1, 100);
//        transaction1 = new Transaction(LocalDateTime.now(), stock1,
//                "Bought TESLA", 5, 10);
//        transaction2 = new Transaction(LocalDateTime.now(), stock2,
//                "Sold AMAXON", 10, 20);
//        ArrayList<Transaction> purchaseHistory = new ArrayList<>(5);
//        purchaseHistory.add(transaction1);
//        ArrayList<Transaction> sellHistory = new ArrayList<>(5);
//        sellHistory.add(transaction2);
//        TransactionHistory transactionHistory = new TransactionHistory(purchaseHistory, sellHistory);
//        Portfolio portfolio = new Portfolio(hashMap, 10000);
//        user = new CommonUser("John", "richboy", LocalDateTime.now(), favourites, portfolio,
//                transactionHistory);
//
//    }
//
//    @Test
//    void successView() {
//        BuyInputData buyInputData = new BuyInputData(stock1, 5);
//        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();
//
//        BuyOutputBoundary buyPresenter = new BuyOutputBoundary() {
//            @Override
//            public void prepareFailView(String message) {
//                fail("Use Case failure is unexpected");
//            }
//
//            @Override
//            public void prepareSuccessView(BuyOutputData buyOutputData) {
//                assertNotNull(buyOutputData.getCreationTime());
//                assertEquals("TESLA",buyOutputData.getStockBought());
//            }
//        };
//        BuyInputBoundary interator = new BuyInteractor(buyDataAccessInterface, user, buyPresenter, stock1);
//        interator.buy(buyInputData);
//    }
//
//    @Test
//    void failureNotEnough(){
//        BuyInputData buyInputData = new BuyInputData(stock1, 2500);
//        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();
//
//        BuyOutputBoundary failurePresenter = new BuyOutputBoundary() {
//            @Override
//            public void prepareFailView(String message) {
//                assertEquals("You do not have enough non-liquid balance to make this purchase, you " +
//                        "can only afford" + 2000 + "stocks", message);
//            }
//
//            @Override
//            public void prepareSuccessView(BuyOutputData buyOutputData) {
//                fail("Use Case success is unexpected");
//            }
//        };
//        BuyInputBoundary interactor = new BuyInteractor(buyDataAccessInterface, user, failurePresenter, stock1);
//        interactor.buy(buyInputData);
//    }
//
//    @Test
//    void failureWrongName(){
//        BuyInputData buyInputData = new BuyInputData(stock2, 10);
//        BuyDataAccessInterface buyDataAccessInterface = new InMemoryStockDataAccessObject();
//
//        BuyOutputBoundary failurePresenter = new BuyOutputBoundary() {
//            @Override
//            public void prepareFailView(String message) {
//                assertEquals("Stock Not Available", message);
//            }
//
//            @Override
//            public void prepareSuccessView(BuyOutputData buyOutputData) {
//                fail("Use Case success is unexpected");
//            }
//        };
//        BuyInputBoundary interactor = new BuyInteractor(buyDataAccessInterface, user, failurePresenter, stock2);
//        interactor.buy(buyInputData);
//    }
//}