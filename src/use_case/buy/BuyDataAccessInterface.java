package use_case.buy;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

public interface BuyDataAccessInterface {
    boolean existsByName(String identifier);

    Stock getStockObject(String stockSymbol);
}
