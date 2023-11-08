package interface_adapter.sell;

public class SellState {
    private String error;

    private String success;

    public SellState(SellState copy){
        error = copy.error;
        success = copy.success;
    }
    public SellState(){}

    public void setSellError(String error) {this.error = error;}
    public String getSellError(){
        return error;
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
