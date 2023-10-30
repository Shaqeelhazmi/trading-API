package use_case.buy;
public class BuyOutputData {
    private String creationTime;

    public BuyOutputData (String creationTime){
        this.creationTime = creationTime;
    }

    public void setCreationTime(String creationTime){ this.creationTime = creationTime;}

    public String getCreationTime(){return creationTime;}
}
