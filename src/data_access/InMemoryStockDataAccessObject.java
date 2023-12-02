package data_access;

import entity.CommonUser;
import entity.PriceHistory;
import entity.Stock;
import entity.Transaction;
import use_case.buy.BuyDataAccessInterface;
import use_case.sell.SellDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStockDataAccessObject implements BuyDataAccessInterface, SellDataAccessInterface {

    private  Map<String, Stock> stocks = new HashMap<>();

    public InMemoryStockDataAccessObject(){
        HashMap<String, Double> daily = new HashMap<>(5);
        daily.put(String.valueOf(LocalDateTime.now().getDayOfMonth()), 10.5);
        HashMap<String, Double> weekly = new HashMap<>();
        HashMap<String, Double> monthly = new HashMap<>();
        PriceHistory priceHistory = new PriceHistory(daily, weekly, monthly);
        Stock stock1 = new Stock("TSLA", priceHistory, "TESLA");
        stocks.put(stock1.getStockSymbol(), stock1);
    }


    @Override
    public boolean existsByName(String identifier) {
        return stocks.containsKey(identifier);
    }

    @Override
    public Stock getStockObject(String stockSymbol) {
//        Cannot extra a stock using a stockSymbol hence using a dummy data

        return stocks.get(stockSymbol);
    }

}