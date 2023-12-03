package use_case.sell;

public class SellInputData {
    private final String stockSymbol;
    private final int amount;
    private final String userName;

    public SellInputData(String stockSymbol, int amount, String userName){
        this.stockSymbol = stockSymbol;
        this.amount = amount;
        this.userName = userName;
    }

    String getStockSymbol() {
        return stockSymbol;
    }

    int getAmount() {
        return amount;
    }
    String getUserName() { return userName; }
}
