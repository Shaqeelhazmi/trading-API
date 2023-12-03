package use_case.searching;

import api.AlphaVantage;
import entity.Stock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary{


    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }
    @Override
    public void search(SearchInputData searchInputData) {
        AlphaVantage alphaVantage = new AlphaVantage("SYMBOL_SEARCH", searchInputData.getStockName());
        JSONObject jsonObject = alphaVantage.getJsonObject();
        List result_list = toList(jsonObject.getJSONArray("bestMatches"));
            // There exist such stocks
            ArrayList symbol_list = new ArrayList<String>();
            ArrayList name_list = new ArrayList<String>();
            HashMap<String, String> stock_list = new HashMap<String, String>();
            for (Object primary_result: result_list){

                HashMap<String, Object> result = (HashMap<String, Object>) primary_result;
                String stocksymbol = result.get("1. symbol").toString();
                String stockname = result.get("2. name").toString();
                symbol_list.add(stocksymbol);
                name_list.add(stockname);
                stock_list.put(stocksymbol, stockname);
            }
            SearchOutputData searchOutputData = new SearchOutputData(stock_list);
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
}
