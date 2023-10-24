package entity;

public class Stock implements StockInterface {
    private final String stockName;
    private final String tickerSymbol;
    private final String category;
    private PriceHistory priceHistory;

    public Stock(String stockName, String tickerSymbol, String category, PriceHistory priceHistory) {
        this.stockName = stockName;
        this.tickerSymbol = tickerSymbol;
        this.category = category;
        this.priceHistory = priceHistory;
    }

    @Override
    public String getStockName() {
        return stockName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(PriceHistory priceHistory) {
        this.priceHistory = priceHistory;
    }
}
