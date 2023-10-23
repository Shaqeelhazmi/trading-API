package entity;

import java.util.ArrayList;

public class CommonAccount implements AccountInterface {
    private String username;
    private String password;
    private ArrayList<Stock> favourites;
    private Portfolio portfolio;
    private TransactionHistory transactionHistory;

    public CommonAccount(String username, String password, ArrayList<Stock> favourites, Portfolio portfolio) {
        this.username = username;
        this.password = password;
        this.favourites = favourites;
        this.portfolio = portfolio;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public ArrayList<Stock> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Stock> favourites) {
        this.favourites = favourites;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
