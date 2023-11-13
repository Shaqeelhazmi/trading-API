package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class AlphaVantage {
    JSONObject jsonObject = new JSONObject();
    public static void main(String[] args) {
        try {
            // Replace 'your_api_key' with your actual Alpha Vantage API key
            String apiKey = "D6WNMN7TXWD695IF";
            String symbol = "TSLA";

            String urlStr = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + "datatype=csv" + apiKey;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response1 = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response1.append(line);
            }

            reader.close();

            symbol = "AMZN";

            String urlStr2 = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + "datatype=csv" + apiKey;
            URL url2 = new URL(urlStr2);
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setRequestMethod("GET");

            // Read the response
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
            StringBuilder response2 = new StringBuilder();
            String line2;

            while ((line2 = reader2.readLine()) != null) {
                response2.append(line2);
            }
            reader.close();


            System.out.println(response1.toString());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}