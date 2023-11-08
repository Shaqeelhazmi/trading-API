package use_case.sell;

import entity.Portfolio;
import entity.Stock;

public interface SellDataAccessInterface {
    boolean existsByName(String identifier);
    void sell(Portfolio portfolio, int amount, Stock stock);
}
