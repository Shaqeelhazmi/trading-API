package api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
        ArrayList<Object> key1 = new ArrayList<>();
        ArrayList<Object> key2 = new ArrayList<>();
        ArrayList<Object> value1 = new ArrayList<>();
        ArrayList<Object> value2 = new ArrayList<>();
        for(Object each_key: search_symbol.getJsonObject().keySet()){
            key1.add(each_key);
            value1.add(search_symbol.getJsonObject().get(each_key.toString()));
        }
        for(Object each_key1: result1.keySet()){
            key2.add(each_key1);
            value2.add(search_symbol.getJsonObject().get(each_key1.toString()));
        }
        assertEquals(key1, key2);
        assertEquals(value1, value2);

        // Test2: "TIME_SERIES_DAILY"
        HttpURLConnection connection2 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo").openConnection();
        String response2 = new String(connection2.getInputStream().readAllBytes());
        JSONObject result2 = new JSONObject(response2);
        ArrayList<Object> key3 = new ArrayList<>();
        ArrayList<Object> key4 = new ArrayList<>();
        ArrayList<Object> value3 = new ArrayList<>();
        ArrayList<Object> value4 = new ArrayList<>();
        for(Object each_key: daily_stock.getJsonObject().keySet()){
            key3.add(each_key);
            value3.add(daily_stock.getJsonObject().get(each_key.toString()));
        }
        for(Object each_key1: result2.keySet()){
            key4.add(each_key1);
            value4.add(result2.get(each_key1.toString()));
        }
        assertEquals(key3, key4);
        assertEquals(value3, value4);

        // Test3: "TIME_SERIES_WEEKLY"
        HttpURLConnection connection3 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&apikey=demo").openConnection();
        String response3 = new String(connection3.getInputStream().readAllBytes());
        JSONObject result3 = new JSONObject(response3);
        ArrayList<Object> key5 = new ArrayList<>();
        ArrayList<Object> key6 = new ArrayList<>();
        ArrayList<Object> value5 = new ArrayList<>();
        ArrayList<Object> value6 = new ArrayList<>();
        for(Object each_key: weekly_stock.getJsonObject().keySet()){
            key5.add(each_key);
            value5.add(weekly_stock.getJsonObject().get(each_key.toString()));
        }
        for(Object each_key1: result3.keySet()){
            key6.add(each_key1);
            value6.add(result3.get(each_key1.toString()));
        }
        assertEquals(key5, key6);
        assertEquals(value5, value6);

        // Test4: "TIME_SERIES_MONTHLY"
        HttpURLConnection connection4 = (HttpURLConnection) new URL("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&apikey=demo").openConnection();
        String response4 = new String(connection4.getInputStream().readAllBytes());
        JSONObject result4 = new JSONObject(response4);
        ArrayList<Object> key7 = new ArrayList<>();
        ArrayList<Object> key8 = new ArrayList<>();
        ArrayList<Object> value7 = new ArrayList<>();
        ArrayList<Object> value8 = new ArrayList<>();
        for(Object each_key: monthly_stock.getJsonObject().keySet()){
            key7.add(each_key);
            value7.add(monthly_stock.getJsonObject().get(each_key.toString()));
        }
        for(Object each_key1: result4.keySet()){
            key8.add(each_key1);
            value8.add(result4.get(each_key1.toString()));
        }
        assertEquals(key7, key8);
        assertEquals(value7, value8);
    }
}