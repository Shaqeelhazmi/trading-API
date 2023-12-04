package interface_adapter.buy;

public class BuyState {


    private String error;

    private String success;

    private boolean failed = false;

    private boolean successed = false;

    private int amount;

    private String username;
    private String stockSymbol;

    private String stockName;

    public String getUsername(){return username; }

    public void SetBuyError(String error) {this.error = error;}

    public String getError() {
        return error;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public boolean getFailed(){return failed;}

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void SetBuySuccess(String success) {this.success = success; }

    public String getBuySuccess(){return success;}

    public boolean getSuccessed(){return successed;}

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

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

    public void setUsername(String username) {this.username = username;}


}
