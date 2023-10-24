package entity;

import java.time.LocalDateTime;

public interface TransactionInterface {
    public LocalDateTime getTimestamp();

    public Stock getStock();

    public String getAction();

    public double getPricePerShare();

    public int getAmountOfShares();
}
