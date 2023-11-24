package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private final LocalDateTime creationTime;
    private ArrayList<Stock> favourites;
    private Portfolio portfolio;
    private final ArrayList<Transaction> transactions;

    public CommonUser(String username, String password, LocalDateTime creationTime, ArrayList<Stock> favourites, Portfolio portfolio,
                      ArrayList<Transaction> transactions) {
        this.username = username;
        this.password = password;
        this.creationTime = creationTime;
        this.favourites = favourites;
        this.portfolio = portfolio;
        this.transactions = transactions;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
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

    public ArrayList<Transaction> getTransactionHistory() {
        return transactions;
    }

}
