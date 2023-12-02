package interface_adapter.sell;

public class SellState {
    private String error;
    private String success;
    private int amount;
    private String username;
    private String stockSymbol;
    private String stockName;

    public void setSellError(String error) {this.error = error;}
    public String getSellError(){
        return error;
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

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void setSellSuccess() {this.success = success; }
    public String getSellSuccess(){
        return success;
    }
}
