package use_case.sell;

import entity.Stock;
public class SellInputData {
    private final Stock stock;
    private final int amount;

    public SellInputData(Stock stock, int amount){
        this.stock = stock;
        this.amount = amount;
    }

    String getStockname() {
        return stock.getStockSymbol();
    }

    int getAmount() {
        return amount;
    }
}
