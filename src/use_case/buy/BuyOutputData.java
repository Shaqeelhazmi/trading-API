package use_case.buy;
public class BuyOutputData {
    private String creationTime;

    private final String stock;

    private final int amount;

    public BuyOutputData (String stock, String creationTime, int amount){
        this.creationTime = creationTime;
        this.stock = stock;
        this.amount = amount;
    }

    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}

    public String getStockBought() {return stock;}

    public int getAmount(){return amount;}
}
