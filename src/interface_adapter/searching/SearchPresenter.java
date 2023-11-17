package interface_adapter.searching;

import interface_adapter.ViewManagerModel;
import use_case.searching.SearchOutputBoundary;
import use_case.searching.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private final SearchViewModel searchViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel){
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareNotAvailable(String message){
        SearchState searchState = searchViewModel.getSearchState();
        searchState.SetSearchError(message);
        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        SearchState searchState = searchViewModel.getSearchState();
        searchState.SetSearchSuccess();
        searchViewModel.firePropertyChanged();
    }
}
