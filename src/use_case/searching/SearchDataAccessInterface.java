package use_case.searching;

import entity.Portfolio;
import entity.Stock;

public interface SearchDataAccessInterface {
    boolean existsByName(String identifier);

    void search(String StockName);
}
