package interface_adapter.portfolio;

import java.util.HashMap;

public class PortfolioState {
    private String error;

    private String success;

    private String username;
    private HashMap<String, Integer> stocksOwned;

    private double accountBalance;

    @Override
    public String toString(){
        return "Error: " + error;
    }
    public void setSuccess() { this.success = success; }
    public void setError(String error) { this.error = error; }
    public void setUsername(String username) { this.username = username; }
    public void setStocksOwned(HashMap<String, Integer> stocksOwned) { this.stocksOwned = stocksOwned; }
    public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }
    public String getSuccess() { return success; }
    public String getError() { return error; }
    public String getUsername() { return username; }
    public HashMap<String, Integer> getStocksOwned() { return stocksOwned; }
    public double getAccountBalance() { return accountBalance; }

}
