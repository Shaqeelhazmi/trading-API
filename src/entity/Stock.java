package entity;

public class Stock implements StockInterface {
    private final String stockSymbol;
    private PriceHistory priceHistory;

    public Stock(String stockSymbol, PriceHistory priceHistory) {
        this.stockSymbol = stockSymbol;
        this.priceHistory = priceHistory;
    }

    @Override
    public String getStockSymbol() {
        return stockSymbol;
    }


    @Override
    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(PriceHistory priceHistory) {
        this.priceHistory = priceHistory;
    }
}
