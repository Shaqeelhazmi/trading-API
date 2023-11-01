package use_case.buy;
public class BuyOutputData {
    private String creationTime;

    private final String stock;

    public BuyOutputData (String stock, String creationTime){
        this.creationTime = creationTime;
        this.stock = stock;
    }

    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}

    public String getStockBought() {return stock;}
}
