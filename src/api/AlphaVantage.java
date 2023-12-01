package api;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;


public class AlphaVantage {
    private JSONObject jsonObject;

    public AlphaVantage(String function, String argument) {
        try {
            HttpURLConnection connection;
            if (function.equals("SYMBOL_SEARCH")) {
                connection = (HttpURLConnection) new URL(
                        "https://www.alphavantage.co/query?function=" + function + "&keywords=" + argument + "&apikey=D6WNMN7TXWD695IF").openConnection();
            } else {
                connection = (HttpURLConnection) new URL(
                        "https://www.alphavantage.co/query?function=" + function + "&symbol=" + argument + "&apikey=D6WNMN7TXWD695IF").openConnection();
            }
            InputStream inputStream = connection.getInputStream();
            String response = new String(inputStream.readAllBytes());
            System.out.println(response);
            this.jsonObject = new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }
}