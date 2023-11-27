package use_case.buy;


public class BuyInputData {
    private final String stockName;
    private final int amount;

    public BuyInputData(String stockName, int amount){
        this.stockName = stockName;
        this.amount = amount;
    }

    String getStockname() {return stockName;}

    int getAmount() {return amount;}
}
