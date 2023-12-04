package api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AlphaVantageTest {

    // Test four distinct functions of the api

    @Test
    void getJsonObject() throws IOException {
        AlphaVantage search_symbol = new AlphaVantage("SYMBOL_SEARCH", "TESCO");
        AlphaVantage daily_stock = new AlphaVantage("TIME_SERIES_DAILY", "IBM");
        AlphaVantage weekly_stock = new AlphaVantage("TIME_SERIES_WEEKLY", "IBM");
        AlphaVantage monthly_stock = new AlphaVantage("TIME_SERIES_MONTHLY", "IBM");

        //Test1: "SYMBOL_SEARCH"
        HttpURLConnection connection1 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=tesco&apikey=demo").openConnection();
        String response1 = new String(connection1.getInputStream().readAllBytes());
        JSONObject result1 = new JSONObject(response1);
        assertEquals(result1, search_symbol.getJsonObject());

        // Test2: "TIME_SERIES_DAILY"
        HttpURLConnection connection2 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo").openConnection();
        String response2 = new String(connection2.getInputStream().readAllBytes());
        JSONObject result2 = new JSONObject(response2);
        assertEquals(result2, daily_stock.getJsonObject());

        // Test3: "TIME_SERIES_WEEKLY"
        HttpURLConnection connection3 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&apikey=demo").openConnection();
        String response3 = new String(connection3.getInputStream().readAllBytes());
        JSONObject result3 = new JSONObject(response3);
        assertEquals(result3, weekly_stock.getJsonObject());

        // Test4: "TIME_SERIES_MONTHLY"
        HttpURLConnection connection4 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&apikey=demo").openConnection();
        String response4 = new String(connection4.getInputStream().readAllBytes());
        JSONObject result4 = new JSONObject(response4);
        assertEquals(result4, monthly_stock.getJsonObject());
    }

}