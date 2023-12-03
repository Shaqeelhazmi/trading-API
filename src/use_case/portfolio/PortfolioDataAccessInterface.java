package use_case.portfolio;

import entity.Portfolio;

public interface PortfolioDataAccessInterface
{
    boolean existsByName(String identifier);

    Portfolio pullPortfolio(String username);
}
