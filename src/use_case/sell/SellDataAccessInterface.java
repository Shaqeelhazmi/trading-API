package use_case.sell;

import entity.Stock;

public interface SellDataAccessInterface {
    boolean existsByName(String identifier);

    Stock getStock(String stockSymbol);

}
