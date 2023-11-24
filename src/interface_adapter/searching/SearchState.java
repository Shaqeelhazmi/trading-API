package interface_adapter.searching;

public class SearchState {
    private String search_name = null;

    private String error = null;


    public SearchState(SearchState copy) {
        search_name = copy.search_name;
        error = copy.error;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchState() {
    }

    public String getSearchError(){
        return error;
    }

    public String getSearchName() {return search_name; }

    public void SetSearchError(String error) {this.error = error;}

    public void setSearchName(String search_name) {this.search_name = search_name;}


    public String toString(){
        return "Error: " + error;
    }
}
