package entity;

import java.time.LocalDateTime;

public interface TransactionInterface {
    public LocalDateTime getTimestamp();

    public String getStock();

    public String getAction();

    public double getPricePerShare();

    public int getAmountOfShares();
}
