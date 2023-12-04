package interface_adapter.sell;

public class SellState {
    private String error;
    private String success;
    private int amount;
    private String username;
    private String stockSymbol;
    private String stockName;

    private boolean successed = false;

    public void setSellError(String error) {this.error = error;}
    public String getSellError(){
        return error;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public boolean getSuccess() {
        return successed;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getStockSymbol(){return stockSymbol;}
    public void setStockSymbol(String stockSymbol){this.stockSymbol = stockSymbol;}
    public String getStockName(){return stockName;}
    public void setStockName(String stockName) {this.stockName = stockName;}
    public String getUsername(){return username;}
    public void setUsername(String username) {this.username = username;}

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void setSellSuccess(String success) {this.success = success; }
    public String getSellSuccess(){
        return success;
    }
}
