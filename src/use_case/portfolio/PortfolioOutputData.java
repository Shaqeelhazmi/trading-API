package use_case.portfolio;

import java.util.HashMap;

public class PortfolioOutputData {
    private final HashMap<String, Integer> stocksOwned;
    private final double accountBalance;

    public PortfolioOutputData(HashMap<String, Integer> stocksOwned, double accountBalance){
        this.stocksOwned = stocksOwned;
        this.accountBalance = accountBalance;
    }

    public HashMap<String, Integer> getStocksOwned() { return stocksOwned; }

    public double getAccountBalance() { return accountBalance; }

}
