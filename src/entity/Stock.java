package entity;

public class Stock implements StockInterface {
    private final String stockSymbol;
    private final String category;
    private PriceHistory priceHistory;

    public Stock(String stockSymbol, String category, PriceHistory priceHistory) {
        this.stockSymbol = stockSymbol;
        this.category = category;
        this.priceHistory = priceHistory;
    }

    @Override
    public String getStockName() {
        return stockSymbol;
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
