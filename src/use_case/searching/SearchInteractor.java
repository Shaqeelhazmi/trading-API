package use_case.searching;

import api.AlphaVantage;
import entity.Stock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

public class SearchInteractor implements SearchInputBoundary{


    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }
    @Override
    public void search(SearchInputData searchInputData) {
        AlphaVantage alphaVantage = new AlphaVantage("SYMBOL_SEARCH", searchInputData.getStockName());
        JSONObject jsonObject = alphaVantage.getJsonObject();
        List<Object> result_list = toList(jsonObject.getJSONArray("bestMatches"));
            // There exist such stocks
            ArrayList<String> symbol_list = new ArrayList<String>();
            ArrayList<String> name_list = new ArrayList<String>();
            HashMap<String, ArrayList<String>> stock_list = new HashMap<String, ArrayList<String>>();
            ArrayList<String> day_list = new ArrayList<>();
            for (Object primary_result: result_list){
                if (symbol_list.size() <= 4){
                    HashMap<String, Object> result = (HashMap<String, Object>) primary_result;
                    String stocksymbol = result.get("1. symbol").toString();
                    String stockname = result.get("2. name").toString();
                    ArrayList<String> information = new ArrayList<>();
                    information.add(stockname);
                    symbol_list.add(stocksymbol);
                    name_list.add(stockname);
                    stock_list.put(stocksymbol, information);
                }
            }
            for (String symbol: stock_list.keySet()){
                AlphaVantage alphaVantage1 = new AlphaVantage("TIME_SERIES_DAILY", symbol);
                JSONObject jsonObject1 = alphaVantage1.getJsonObject();
                Object object1 = toMap(jsonObject1).get("Time Series (Daily)");
                HashMap<String, HashMap<String, String>> daily = (HashMap<String, HashMap<String, String>>) object1;
                Set<String> all_daily = daily.keySet();
                ArrayList<String> listDaily = sortDateStrings(all_daily);

                for (String day: listDaily){
                    String open = daily.get(day).get("1. open");
                    stock_list.get(symbol).add(open);
                    day_list.add(day);
                }
//                int i = 31;
//                int days;
//                for (days = 0; days < 31; days++) {
//                    LocalDate date = LocalDate.now().minusDays(i);
//                    String open = daily.get(date.toString()).get("1. open");
//                    stock_list.get(symbol).add(open);
//                }
            }
            SearchOutputData searchOutputData = new SearchOutputData(stock_list, day_list);
            searchPresenter.prepareSuccessView(searchOutputData);
        }

    public static HashMap<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }   return map;
    }
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }   return list;
    }
    private static ArrayList<String> sortDateStrings(Set<String> keyset) {
        ArrayList<Integer> datesAsIntegers = new ArrayList<>();
        for (String date : keyset) {
            int intValueDate = Integer.parseInt(date.replace("-", ""));
            datesAsIntegers.add(intValueDate);
        }
        Collections.sort(datesAsIntegers);

        ArrayList<String> sortedDateStrings = new ArrayList<>();
        for (int intValueDate : datesAsIntegers) {
            StringBuilder tempStringBuilder = new StringBuilder(Integer.toString(intValueDate));
            tempStringBuilder.insert(4, "-");
            tempStringBuilder.insert(7, "-");

            String finalString = tempStringBuilder.toString();
            sortedDateStrings.add(finalString);
            if (sortedDateStrings.size() == 100) {break;}
        }

        return sortedDateStrings;
    }
}
