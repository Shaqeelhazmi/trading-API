package use_case.portfolio;

public interface PortfolioOutputBoundary {
    void prepareSuccessView(PortfolioOutputData portfolioOutputData);

    void prepareFailView(String message);
}
