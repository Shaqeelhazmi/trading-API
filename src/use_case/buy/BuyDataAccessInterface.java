package use_case.buy;

import entity.Portfolio;
import entity.Stock;

public interface BuyDataAccessInterface {
    boolean existsByName(String identifier);

    void buy(Portfolio portfolio, int amount, Stock stock);

}
