package entity;

public class Stock implements StockInterface {
    private final String stockSymbol;
    private PriceHistory priceHistory;

    private final String name;

    public Stock(String stockSymbol, String name, PriceHistory priceHistory) {
        this.stockSymbol = stockSymbol;
        this.priceHistory = priceHistory;
        this.name = name;
    }

    @Override
    public String getStockSymbol() {
        return stockSymbol;
    }

    @Override
    public String getStockName() {
        return name;
    }

    @Override
    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(PriceHistory priceHistory) {
        this.priceHistory = priceHistory;
    }
}
