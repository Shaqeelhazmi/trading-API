package app;


import data_access.StockDataAccessObject;
import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.searching.SearchController;
import interface_adapter.searching.SearchPresenter;
import interface_adapter.searching.SearchViewModel;
import use_case.searching.SearchDataAccessInterface;
import use_case.searching.SearchInputBoundary;
import use_case.searching.SearchInteractor;
import use_case.searching.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {
        try {
            SearchController searchController =  createSearchUseCase(viewManagerModel, searchViewModel);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file");
        }
        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) throws IOException{
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel);
        SearchInputBoundary userSearchInteractor = new SearchInteractor(searchOutputBoundary);

        return new SearchController(userSearchInteractor);
    }
}
