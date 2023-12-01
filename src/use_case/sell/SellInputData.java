package use_case.sell;
import java.time.LocalDateTime;

import entity.Stock;
public class SellInputData {
    private final String stockName;
    private final int amount;
    private final String userName;

    public SellInputData(String stockName, int amount, String userName){
        this.stockName = stockName;
        this.amount = amount;
        this.userName = userName;
    }

    String getStockname() {
        return stockName;
    }

    int getAmount() {
        return amount;
    }
    String getUserName() { return userName; }
}
