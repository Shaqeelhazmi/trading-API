package interface_adapter.buy;

public class BuyState {


    private String error;

    private String success;

    public void SetBuyError(String error) {this.error = error;}

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void SetBuySuccess() {this.success = success; }
}
