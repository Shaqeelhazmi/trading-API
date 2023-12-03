package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioPresenter;
import interface_adapter.portfolio.PortfolioViewModel;
import use_case.portfolio.PortfolioDataAccessInterface;
import use_case.portfolio.PortfolioInputBoundary;
import use_case.portfolio.PortfolioInteractor;
import use_case.portfolio.PortfolioOutputBoundary;
import view.PortfolioView;

import javax.swing.*;
import java.io.IOException;

public class PortfolioUseCaseFactory {
    public static PortfolioView create(ViewManagerModel viewManagerModel, PortfolioViewModel portfolioViewModel,
                                       LoggedInViewModel loggedInViewModel, PortfolioDataAccessInterface portfolioDataAccessObject) {
        try {
            PortfolioController portfolioController =  createPortfolioUseCase(viewManagerModel, portfolioViewModel, portfolioDataAccessObject);
            return new PortfolioView(portfolioController, portfolioViewModel, viewManagerModel, loggedInViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file");
        }
        return null;
    }

    private static PortfolioController createPortfolioUseCase(ViewManagerModel viewManagerModel, PortfolioViewModel portfolioViewModel,
                                                  PortfolioDataAccessInterface portfolioDataAccessObject) throws IOException{
        PortfolioOutputBoundary portfolioOutputBoundary = new PortfolioPresenter(viewManagerModel, portfolioViewModel);
        PortfolioInputBoundary portfolioInteractor = new PortfolioInteractor(portfolioDataAccessObject, portfolioOutputBoundary);

        return new PortfolioController(portfolioInteractor);
    }
}
