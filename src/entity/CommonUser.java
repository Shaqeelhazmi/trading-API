package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private final LocalDateTime creationTime;
    private List<String> favourites;
    private Portfolio portfolio;
    private ArrayList<Transaction> transactionHistory;

    public CommonUser(String username, String password, LocalDateTime creationTime, List<String> favourites, Portfolio portfolio, ArrayList<Transaction> transactionHistory) {
        this.username = username;
        this.password = password;
        this.creationTime = creationTime;
        this.favourites = favourites;
        this.portfolio = portfolio;
        this.transactionHistory = transactionHistory;
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

    public List<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
