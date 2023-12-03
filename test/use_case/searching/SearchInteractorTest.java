/*
package use_case.searching;

import data_access.StockDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    String search1;

    String search2;

    @BeforeEach
    void init(){
        search1 = "TSLA";
    }

    @Test
    void successView() throws IOException {
        SearchInputData searchInputData = new SearchInputData(search1);
        SearchDataAccessInterface searchDataAccessInterface = new StockDataAccessObject("./stocks.json");

        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareFailView(String message) {
                fail("Use Case failure is not expected");
            }

            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                HashMap<String, ArrayList<String>> stock1 = new HashMap<>();
                ArrayList<String> information1 = new ArrayList<>();
                information1.add("Tesla Inc");
                information1.add("158.0");
                stock1.put("TSLA", information1);
                assertEquals(stock1, searchOutputData.getStocks());
            }
        };
        SearchInputBoundary interactor = new SearchInteractor(searchPresenter);
        interactor.search(searchInputData);
    }
}
*/