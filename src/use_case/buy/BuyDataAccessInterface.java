package use_case.buy;

import entity.Stock;

public interface BuyDataAccessInterface {
    boolean existsByName(String identifier);

    Stock getStock(String stockSymbol);

}
