package use_case.buy;

import entity.Portfolio;

public interface BuyDataAccessInterface {
    boolean existsByName(String identifier);

    void buy(Portfolio portfolio);
}
