package use_case.sell;
import java.time.LocalDateTime;

import entity.Stock;
public class SellInputData {
    private final String stockName;
    private final int amount;

    public SellInputData(String stockName, int amount){
        this.stockName = stockName;
        this.amount = amount;
    }

    String getStockname() {
        return stockName;
    }

    int getAmount() {
        return amount;
    }
}
