package use_case.buy;


public class BuyInputData {
    private final String stockSymbol;
    private final int amount;

    private final String userName;

    public BuyInputData(String stockSymbol, int amount, String userName){
        this.stockSymbol = stockSymbol;
        this.amount = amount;
        this.userName = userName;
    }

    String getStockSymbol() {return stockSymbol;}

    int getAmount() {return amount;}

    String getUserName() {return userName;}
}
