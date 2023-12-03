package use_case.searching;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SearchOutputData {
    private final HashMap<String, ArrayList<String>> stocks;

    public SearchOutputData(HashMap<String, ArrayList<String>> stocks) {
        this.stocks = stocks;
    }

    public HashMap<String, ArrayList<String>> getStocks() {
        return stocks;
    }

    public Set<String> getStocksSymbols() {return stocks.keySet();}


}
