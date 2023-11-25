package entity;

import java.time.LocalDateTime;

public class Transaction implements TransactionInterface{
    private final LocalDateTime timestamp;
    private final String stockName;
    private final String action;
    private final double pricePerShare;
    private final int amountOfShares;

    public Transaction(LocalDateTime timestamp, String stockName, String action, double pricePerShare, int amountOfShares) {
        this.timestamp = timestamp;
        this.stockName = stockName;
        this.action = action;
        this.pricePerShare = pricePerShare;
        this.amountOfShares = amountOfShares;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getStock() {
        return stockName;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public double getPricePerShare() {
        return pricePerShare;
    }

    @Override
    public int getAmountOfShares() {
        return amountOfShares;
    }
}
