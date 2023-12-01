package interface_adapter.sell;

public class SellState {
    private String error;

    private String success;
    private int amount = 0;

    public SellState(SellState copy){
        error = copy.error;
        success = copy.success;
        amount = copy.amount;
    }
    public SellState(){}

    public void setSellError(String error) {this.error = error;}
    public String getSellError(){
        return error;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void setSellSuccess() {this.success = success; }
    public String getSellSuccess(){
        return success;
    }
}
