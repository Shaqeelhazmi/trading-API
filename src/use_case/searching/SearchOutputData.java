package use_case.searching;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SearchOutputData {
    private final HashMap<String, String> stocks;

    public SearchOutputData(HashMap<String, String> stocks) {
        this.stocks = stocks;
    }

    public HashMap<String, String> getStocks() {
        return stocks;
    }

    public Set<String> getStocksSymbols() {return stocks.keySet();}

    public Collection<String> getStocksNames() {return stocks.values();}
}
