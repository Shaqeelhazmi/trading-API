package use_case.sell;

public class SellOutputData {
    private final String stockName;
    private String creationTime;

    private double accountBalance;
    public SellOutputData(String stockName, String creationTime, double accountBalance) {
        this.stockName = stockName;
        this.creationTime = creationTime;
        this.accountBalance =accountBalance;

    }

    public String getStockSold() {
        return stockName;
    }
    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}
