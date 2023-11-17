package interface_adapter.searching;

public class SearchState {
    private String error;

    private String success;

    public void SetSearchError(String error) {this.error = error;}

    @Override
    public String toString(){
        return "Error: " + error;
    }

    public void SetSearchSuccess() {this.success = success; }
}
