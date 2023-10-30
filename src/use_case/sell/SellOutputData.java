package use_case.sell;
public class SellOutputData {
    private final String stockName;
    private final int amount;
    private boolean useCaseFailed;

    public SellOutputData(String stockName, int amount, boolean useCaseFailed) {
        this.stockName = stockName;
        this.amount = amount;
        this.useCaseFailed = useCaseFailed;
    }

    public String getStockName() {
        return stockName;
    }
    public int getAmount(){
        return amount;
    }
}
