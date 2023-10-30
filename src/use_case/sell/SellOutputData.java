package use_case.sell;
import java.time.LocalDateTime;

public class SellOutputData {
    private final String stockName;
    private final int amount;
    private final LocalDateTime timeStamp;
    private boolean useCaseFailed;

    public SellOutputData(String stockName, int amount, LocalDateTime timeStamp, boolean useCaseFailed) {
        this.stockName = stockName;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.useCaseFailed = useCaseFailed;
    }

    public String getStockName() {
        return stockName;
    }
    public int getAmount(){
        return amount;
    }
    public LocalDateTime getTimeStamp(){return timeStamp; }
}
