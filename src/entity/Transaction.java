package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Transaction implements TransactionInterface{
    private final LocalDateTime timestamp;
    private final String stock;
    private final String action;
    private final double pricePerShare;
    private final int amountOfShares;

    public Transaction(LocalDateTime timestamp, String stock, String action, double pricePerShare, int amountOfShares) {
        this.timestamp = timestamp;
        this.stock = stock;
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
        return stock;
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

    public HashMap<String, Object> getTransactionMap() {
        HashMap<String, Object> transactionMap = new HashMap<>();
        transactionMap.put("timestamp", timestamp);
        transactionMap.put("stock", stock);
        transactionMap.put("action", action);
        transactionMap.put("pricePerShare", pricePerShare);
        transactionMap.put("amountOfShares", amountOfShares);
        return transactionMap;
    }
}
