package data_access;

import entity.Stock;
import use_case.buy.BuyDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.io.*;
import java.util.*;

public class StockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Stock> stocks = new HashMap<>();

    public StockDataAccessObject(File csvFile) {
        this.csvFile = csvFile;
    }

    public boolean existsByName(String identifier) {
        //left
        return false;
    }
}
