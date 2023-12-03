package interface_adapter.portfolio;

import use_case.portfolio.PortfolioInputBoundary;
import use_case.portfolio.PortfolioInputData;

public class PortfolioController {
    final PortfolioInputBoundary portfolioInteractor;

    public PortfolioController(PortfolioInputBoundary portfolioInteractor) {
        this.portfolioInteractor = portfolioInteractor;
    }

    public void execute(String username) {
        PortfolioInputData portfolioInputData = new PortfolioInputData(username);
        portfolioInteractor.execute(portfolioInputData);
    }
}
