package interface_adapter.buy;

import entity.Stock;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;
import use_case.buy.BuyInteractor;

import java.io.IOException;

public class BuyController {
    final BuyInputBoundary BuyInteractor;

    public BuyController (BuyInputBoundary buyInteractor){
        this.BuyInteractor = buyInteractor;
    }
    public void buy(String stockSymbol, int amount, String userName) throws IOException {
        BuyInputData buyInputData = new BuyInputData(stockSymbol, amount, userName);
        BuyInteractor.buy(buyInputData);
    }
}
