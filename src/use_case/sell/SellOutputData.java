package use_case.sell;

public class SellOutputData {
    private final String stockName;
    private String creationTime;

    public SellOutputData(String stockName, String creationTime) {
        this.stockName = stockName;
        this.creationTime = creationTime;
    }

    public String getStockSold() {
        return stockName;
    }
    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}
}
