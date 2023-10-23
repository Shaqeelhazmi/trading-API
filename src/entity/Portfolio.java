package entity;

import java.util.HashMap;

public class Portfolio {
    private HashMap<Stock, Integer> portfolio;
    private double accountBalance;

    public HashMap<Stock, Integer> getPortfolio() {
        return portfolio;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
