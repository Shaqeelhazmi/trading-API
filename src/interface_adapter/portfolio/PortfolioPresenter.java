package interface_adapter.portfolio;

import interface_adapter.ViewManagerModel;
import interface_adapter.sell.SellState;
import use_case.portfolio.PortfolioOutputBoundary;
import use_case.portfolio.PortfolioOutputData;

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PortfolioPresenter implements PortfolioOutputBoundary{
    private final PortfolioViewModel portfolioViewModel;
    private final ViewManagerModel viewManagerModel;

    public PortfolioPresenter(ViewManagerModel viewManagerModel, PortfolioViewModel portfolioViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.portfolioViewModel = portfolioViewModel;
    }
    @Override
    public void prepareSuccessView(PortfolioOutputData portfolioOutputData) {
        PortfolioState portfolioState = portfolioViewModel.getState();
        portfolioState.setStocksOwned(portfolioOutputData.getStocksOwned());
        portfolioState.setSuccess();
        portfolioViewModel.firePropertyChanged();
    }

}
