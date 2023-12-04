package interface_adapter.searching;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyState;

import interface_adapter.buy.BuyViewModel;
import interface_adapter.stock.StockViewModel;
import use_case.searching.SearchOutputBoundary;
import use_case.searching.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private final SearchViewModel searchViewModel;

    private final StockViewModel stockViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, StockViewModel stockViewModel){
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.stockViewModel =stockViewModel;
    }

    @Override
    public void prepareFailView(String message){
        SearchState searchState = searchViewModel.getSearchState();
        searchState.SetSearchError(message);
        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        SearchState state = searchViewModel.getSearchState();
        state.updatedStoredStocks(searchOutputData.getStocks());
        state.setDay_list(searchOutputData.getDay_list());


        searchViewModel.setState(state);
        searchViewModel.firePropertyChanged();
    }
}
