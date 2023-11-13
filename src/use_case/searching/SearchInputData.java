package use_case.searching;

public class SearchInputData {
    private final String StockName;

    public SearchInputData(String StockName) {
        this.StockName = StockName;
    }

    public String getStockName() {return StockName; }
}
