package use_case.buy;

import entity.Stock;

public class BuyInputData {
    private final Stock stock;
    private final int amount;

    public BuyInputData(Stock stock, int amount){
        this.stock = stock;
        this.amount = amount;
    }

    String getStockname() {return stock.name;}

    int getAmount() {return amount;}
}
