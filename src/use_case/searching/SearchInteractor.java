package use_case.searching;

import entity.Stock;

public class SearchInteractor implements SearchInputBoundary{

    private final String stock;

    private final SearchDataAccessInterface searchingDataAccessObject;

    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(String stock, SearchDataAccessInterface searchingDataAccessObject, SearchOutputBoundary searchPresenter) {
        this.stock = stock;
        this.searchingDataAccessObject = searchingDataAccessObject;
        this.searchPresenter = searchPresenter;
    }
    @Override
    public void search(SearchInputData searchInputData) {
        if (!searchingDataAccessObject.existsByName(searchInputData.getStockName())) {
            searchPresenter.prepareNotAvailable("No such stock found");
        } else {
            searchingDataAccessObject.search(searchInputData.getStockName());
            SearchOutputData searchOutputData = new SearchOutputData(searchInputData.getStockName());
            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
