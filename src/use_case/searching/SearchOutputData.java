package use_case.searching;

public class SearchOutputData {
    private final String stock;

    public SearchOutputData(String stock) {
        this.stock = stock;
    }

    public String getStockName() {
        return stock;
    }
}
