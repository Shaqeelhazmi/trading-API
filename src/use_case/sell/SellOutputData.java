package use_case.sell;
import java.time.LocalDateTime;

public class SellOutputData {
    private final String stockName;
    private String creationTime;
    private boolean useCaseFailed;

    public SellOutputData(String stockName, String creationTime, boolean useCaseFailed) {
        this.stockName = stockName;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getStockName() {
        return stockName;
    }
    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}
}
