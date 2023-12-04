package use_case.buy;
public class BuyOutputData {
    private String creationTime;

    private final String stock;

    private final int amount;

    private final double balance;

    public BuyOutputData (String stock, String creationTime, int amount, double balance){
        this.creationTime = creationTime;
        this.stock = stock;
        this.amount = amount;
        this.balance = balance;
    }

    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}

    public String getStockBought() {return stock;}

    public int getAmount(){return amount;}

    public double getBalance() {
        return balance;
    }
}
