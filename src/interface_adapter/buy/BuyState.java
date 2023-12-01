package interface_adapter.buy;

public class BuyState {


    private String error;

    private String success;

    private int amount;

    private String username;
    private String stockSymbol;

    private String stockName;

    public String getUsername(){return username; }

    public void SetBuyError(String error) {this.error = error;}

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void SetBuySuccess() {this.success = success; }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setStockSymbol(String stockSymbol){this.stockSymbol = stockSymbol;}

    public void setStockName(String stockName) {this.stockName = stockName;}

    public String getStockSymbol(){return stockSymbol;}

    public String getStockName(){return stockName;}


}
