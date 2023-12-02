package use_case.searching;

import entity.PriceHistory;
import entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchingOutputDataTest {

    HashMap<String, String> stocks = new HashMap<String, String>();
    stocks.put("TSLA", "Tesla Inc");
    SearchOutputData searchOutputData;

    @BeforeEach
    void init(){
        searchOutputData = new SearchOutputData(stocks);
    }

    @Test
    void getStocks() {
        assertEquals({"TSLA": "Tesla Inc"}, searchOutputData.getStocks());
    }

    @Test
    void getStocksSymbols() {
        assertEquals("TSLA", searchOutputData.getStocksSymbols);
    }

    @Test
    void getStocksNames() {
        assertEquals("Tesla Inc", searchOutputData.getStocksNames());
    }
}