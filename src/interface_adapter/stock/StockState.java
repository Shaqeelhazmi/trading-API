package interface_adapter.stock;

import java.util.ArrayList;

public class StockState {
    private String stockSymbol;
    private String stockName;
    private ArrayList<String> dailyPriceHistoryDates;
    private ArrayList<Double> dailyPriceHistoryPrices;

    public StockState(StockState copy) {
        this.stockSymbol = copy.stockSymbol;
        this.stockName = copy.stockName;
    }
    public StockState() {}

    public String getStockSymbol() {return stockSymbol;}

    public String getStockName() {
        return stockName;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
