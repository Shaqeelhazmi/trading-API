package use_case.buy;

import entity.Stock;

public interface BuyDataAccessInterface {
    boolean existsByName(String identifier);

    Stock getStockObject(String stockSymbol);

}
