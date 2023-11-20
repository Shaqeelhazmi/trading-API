package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class AlphaVantage {
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
            JSONObject jsonObject1 = new JSONObject();
            String line;

            Integer current_line = 0;
            while ((line = reader.readLine()) != null) {
                response1.append(line);
                // record json key as the date of the stock
                String json_key = null;
                if ((current_line - 9) % 7 == 0) {
                    json_key = line;
                }
                // record json
                String json_value = null;
                if ((current_line - 10) % 7 == 0) {
                    json_value = line;
                }
                jsonObject1.put(json_key, json_value);
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
            JSONObject jsonObject2 = new JSONObject();
            String line2;

            current_line = 0;
            while ((line2 = reader2.readLine()) != null) {
                response2.append(line2);
                String json_key = null;
                if ((current_line - 9) % 7 == 0) {
                    json_key = line;
                }
                // record json
                String json_value = null;
                if ((current_line - 10) % 7 == 0) {
                    json_value = line;
                }
                jsonObject2.put(json_key, json_value);
            }
            reader.close();


            System.out.println(response1.toString());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
