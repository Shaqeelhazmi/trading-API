package use_case.searching;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchOutputData {
    private final HashMap<String, ArrayList<String>> stocks;

    public SearchOutputData(HashMap<String, ArrayList<String>> stocks) {
        this.stocks = stocks;
    }

    public HashMap<String, ArrayList<String>> getStocks() {
        return stocks;
    }

    public ArrayList<String> getStocksSymbols() {
        ArrayList<String> stocksSymbols = new ArrayList<>();
        for (String stockSymbol: stocks.keySet()){
            stocksSymbols.add(stockSymbol);
        }
        return stocksSymbols;
    }

    public ArrayList<String> getStocksNames() {
        ArrayList<String>stocksNames = new ArrayList<>();
        for (ArrayList<String> stockName: stocks.values()){
            stocksNames.add(stockName.get(0));
        }
        return stocksNames;
    }
}
