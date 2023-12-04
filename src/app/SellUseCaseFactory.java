package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellPresenter;
import interface_adapter.sell.SellViewModel;
import entity.CommonUser;
import entity.Stock;
import interface_adapter.stock.StockViewModel;
import use_case.sell.SellDataAccessInterface;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInteractor;
import use_case.sell.SellOutputBoundary;
import interface_adapter.logged_in.LoggedInViewModel;
import view.SellView;
import data_access.FileUserDataAccessObject;

import javax.swing.*;
import java.io.IOException;

public class SellUseCaseFactory {
    /** Prevent instantiation. */
    private SellUseCaseFactory() {}
    public static SellView create(ViewManagerModel viewManagerModel, SellViewModel sellViewModel,
                                  SellDataAccessInterface userDataAccessObject, LoggedInViewModel loggedInViewModel,
                                  SearchViewModel searchViewModel, FileUserDataAccessObject fileUserDataAccessObject ,StockViewModel stockViewModel){
        try {
            SellController sellController = createSellUseCase(viewManagerModel, sellViewModel, userDataAccessObject, searchViewModel,fileUserDataAccessObject, stockViewModel);
            return new SellView(sellController, sellViewModel, loggedInViewModel, searchViewModel, viewManagerModel, stockViewModel );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to create");
        }
        return null;
    }

    private static SellController createSellUseCase(ViewManagerModel viewManagerModel, SellViewModel sellViewModel,
                                                    SellDataAccessInterface userDataAccessObject,
                                                    SearchViewModel searchViewModel, FileUserDataAccessObject fileUserDataAccessObject,
                                                    StockViewModel stockViewModel) throws IOException{
        SellOutputBoundary sellOutputBoundary = new SellPresenter(viewManagerModel, sellViewModel, searchViewModel, stockViewModel);
        SellInputBoundary userSellInteractor = new SellInteractor(userDataAccessObject, sellOutputBoundary, fileUserDataAccessObject);

        return new SellController(userSellInteractor);
    }
}
