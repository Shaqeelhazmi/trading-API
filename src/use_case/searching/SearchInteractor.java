package use_case.searching;

import entity.Stock;

public class SearchInteractor implements SearchInputBoundary{

    private final Stock stock;

    private final SearchDataAccessInterface searchingDataAccessObject;

    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(Stock stock, SearchDataAccessInterface searchingDataAccessObject, SearchOutputBoundary searchPresenter) {
        this.stock = stock;
        this.searchingDataAccessObject = searchingDataAccessObject;
        this.searchPresenter = searchPresenter;
    }
    @Override
    public void search(SearchInputData searchInputData) {
        if (!searchingDataAccessObject.existsByName(stock.getStockName())) {
            searchPresenter.prepareNotAvailable("Stock Not available: No Such Stock");
        } else {
            searchingDataAccessObject.search(searchInputData.getStockName());

            SearchOutputData searchOutputData = new SearchOutputData();
            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
