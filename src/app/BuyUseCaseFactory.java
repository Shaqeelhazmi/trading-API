package app;

import data_access.FileUserDataAccessObject;
import data_access.StockDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyPresenter;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.stock.StockViewModel;
import use_case.buy.BuyDataAccessInterface;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInteractor;
import use_case.buy.BuyOutputBoundary;
import view.BuyView;

import javax.swing.*;
import java.io.IOException;

public class BuyUseCaseFactory {

    public static BuyView create(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel,
                                 BuyDataAccessInterface buyDataAccessObject, LoggedInViewModel loggedInViewModel, SearchViewModel searchViewModel,
                                 FileUserDataAccessObject userDataAccessObject, StockViewModel stockViewModel) {
        try {
            BuyController buyController =  createBuyUseCase(viewManagerModel, buyViewModel, buyDataAccessObject, userDataAccessObject, searchViewModel, stockViewModel, loggedInViewModel);
            return new BuyView(buyController, buyViewModel, loggedInViewModel, searchViewModel, viewManagerModel, stockViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to create");
        }
        return null;
    }
    private static BuyController createBuyUseCase(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel,
                                                  BuyDataAccessInterface buyDataAccessInterface,
                                                  FileUserDataAccessObject userDataAccessObject, SearchViewModel searchViewModel,
                                                  StockViewModel stockViewModel, LoggedInViewModel loggedInViewModel) throws IOException{
        BuyOutputBoundary buyOutputBoundary = new BuyPresenter(viewManagerModel, buyViewModel, searchViewModel, stockViewModel, loggedInViewModel);
        BuyInputBoundary userBuyInteractor = new BuyInteractor(buyDataAccessInterface, buyOutputBoundary, userDataAccessObject);

        return new BuyController(userBuyInteractor);
    }
}
