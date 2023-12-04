package use_case.searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchingOutputDataTest {

    HashMap<String, ArrayList<String>> stocks = new HashMap<>();
    SearchOutputData searchOutputData;

    @BeforeEach
    void init(){
        ArrayList<String> information = new ArrayList<>();
        information.add("Tesla Inc");
        information.add("158.0");
        stocks.put("TSLA", information);
        ArrayList<String> day_list = new ArrayList<>();
        day_list.add("2023-12-01");
        searchOutputData = new SearchOutputData(stocks, day_list);
    }

    @Test
    void getStocks() {
        HashMap<String, ArrayList<String>> stock1 = new HashMap<>();
        ArrayList<String> information1 = new ArrayList<>();
        information1.add("Tesla Inc");
        information1.add("158.0");
        stock1.put("TSLA", information1);
        assertEquals(stock1, searchOutputData.getStocks());
    }

    @Test
    void getStocksSymbols() {
        ArrayList<String> stockSymbol1 = new ArrayList<>();
        stockSymbol1.add("TSLA");
        assertEquals(stockSymbol1, searchOutputData.getStocksSymbols());
    }

    @Test
    void getStocksNames() {
        ArrayList<String> stockName1 = new ArrayList<>();
        stockName1.add("Tesla Inc");
        assertEquals(stockName1, searchOutputData.getStocksNames());
    }
}