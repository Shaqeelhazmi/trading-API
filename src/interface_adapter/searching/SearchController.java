package interface_adapter.searching;

import entity.Stock;
import use_case.searching.SearchInputBoundary;
import use_case.searching.SearchInputData;
import use_case.searching.SearchInteractor;

public class SearchController {
    final SearchInputBoundary SearchInteractor;

    public SearchController (SearchInputBoundary searchInteractor){
        this.SearchInteractor = searchInteractor;
    }
    public void search(String stockname){
        SearchInputData searchInputData = new SearchInputData(stockname);
        SearchInteractor.search(searchInputData);
    }
}
