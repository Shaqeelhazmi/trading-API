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
    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    @Override
    public String getStockName() {
        return name;
    }

    public void setPriceHistory(PriceHistory priceHistory) {
        this.priceHistory = priceHistory;
    }
}
