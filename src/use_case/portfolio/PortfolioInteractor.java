package use_case.portfolio;

import entity.Portfolio;

import java.util.HashMap;


public class PortfolioInteractor implements PortfolioInputBoundary{
    PortfolioDataAccessInterface portfolioDataAccessObject;
    PortfolioOutputBoundary portfolioPresenter;

    public PortfolioInteractor(PortfolioDataAccessInterface portfolioDataAccessObject,
                               PortfolioOutputBoundary portfolioPresenter) {
        this.portfolioDataAccessObject = portfolioDataAccessObject;
        this.portfolioPresenter = portfolioPresenter;
    }

    @Override
    public void execute(PortfolioInputData portfolioInputData) {

        Portfolio portfolio = portfolioDataAccessObject.pullPortfolio(portfolioInputData.getUserName());

        HashMap<String, Integer> stocksOwned = portfolio.getPortfolio();
        double accountBalance = portfolio.getAccountBalance();

        if (stocksOwned.isEmpty()){
            portfolioPresenter.prepareFailView("You do not own any stocks. Your account balance is:" + accountBalance);
        } else {
            PortfolioOutputData portfolioOutputData = new PortfolioOutputData(stocksOwned, accountBalance);
            portfolioPresenter.prepareSuccessView(portfolioOutputData);
        }

    }
}
