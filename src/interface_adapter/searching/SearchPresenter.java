package interface_adapter.searching;

import interface_adapter.ViewManagerModel;
import use_case.searching.SearchOutputBoundary;
import use_case.searching.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareNotAvailable(String message){

    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {

    }
}
