package interface_adapter.searching;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchState {
    private String search_name = null;

    private String error = null;

    private ArrayList<String> information = null;

    private HashMap<String, ArrayList<String>> stocks;




    public SearchState(SearchState copy) {
        search_name = copy.search_name;
        error = copy.error;
        stocks = copy.stocks;
        information = copy.information;

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

    public void setInformation(ArrayList<String> information) {this.information = information;}

    public ArrayList<String> getInformation(){ return information;}

    public String toString(){
        return "Search Name: " + search_name;
    }

    public void SetSearchSuccess() {
    }

    public void updatedStoredStocks(HashMap<String, ArrayList<String>> stocks){this.stocks = stocks;}


    public HashMap<String, ArrayList<String>> getStoredStocks(){return stocks;}
}
