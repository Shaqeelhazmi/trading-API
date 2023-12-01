package use_case.sell;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

public interface SellDataAccessInterface {
    boolean existsByName(String identifier);

    Stock getStockObject(String stockSymbol);

}
