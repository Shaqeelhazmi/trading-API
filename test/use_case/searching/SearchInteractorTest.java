//package use_case.searching;
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
//class SearchInteractorTest {
//
//    String search1;
//
//    String search2;
//
//    @BeforeEach
//    void init(){
//        search1 = new "TSLA";
//        search2 = new "AAAAAAAAA";
//    }
//
//    @Test
//    void successView() {
//        SearchInputData searchInputData = new SearchInputData(search1);
//        SearchDataAccessInterface searchDataAccessInterface = new InMemoryStockDataAccessObject();
//
//        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
//            @Override
//            public void prepareFailView(String message) {
//                fail("Use Case failure is unexpected");
//            }
//
//            @Override
//            public void prepareSuccessView(SearchOutputData searchOutputData) {
//                assertEquals("TSLA",searchOutputData.getStockname());
//            }
//        };
//        SearchInputBoundary interator = new SearchInteractor(stock1, searchDataAccessInterface, SearchPresenter);
//        interator.search(searchInputData);
//    }
//
//    @Test
//    void failureWrongName(){
//        SearchInputData searchInputData = new SearchInputData(search2);
//        SearchDataAccessInterface searchDataAccessInterface = new InMemoryStockDataAccessObject();
//
//        SearchOutputBoundary failurePresenter = new SearchOutputBoundary() {
//            @Override
//            public void prepareFailView(String message) {
//                assertEquals("No such stock found", message);
//            }
//
//            @Override
//            public void prepareSuccessView(SearchOutputData searchOutputData) {
//                fail("Use Case success is unexpected");
//            }
//        };
//        SearchInputBoundary interactor = new SearchInteractor(search2, searchDataAccessInterface, failurePresenter);
//        interactor.search(searchInputData);
//    }
//}