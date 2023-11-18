package entity;

import java.util.HashMap;

public class Portfolio implements PortfolioInterface {
    private HashMap<Stock, Integer> portfolio;
    private double accountBalance;

    public Portfolio(HashMap<Stock, Integer> portfolio, double accountBalance) {
        this.accountBalance = accountBalance;
        this.portfolio = portfolio;
    }

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
