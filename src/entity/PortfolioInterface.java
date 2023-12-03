package entity;

import java.util.HashMap;

public interface PortfolioInterface {
    public HashMap<String, Integer> getPortfolio();

    public double getAccountBalance();

    public void setAccountBalance(double accountBalance);
}
