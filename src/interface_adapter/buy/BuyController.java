package interface_adapter.buy;

import entity.Stock;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;
import use_case.buy.BuyInteractor;

public class BuyController {
    final BuyInputBoundary BuyInteractor;

    public BuyController (BuyInputBoundary buyInteractor){
        this.BuyInteractor = buyInteractor;
    }
    public void buy(Stock stock, int amount){
        BuyInputData buyInputData = new BuyInputData(stock, amount);
        BuyInteractor.buy(buyInputData);
    }
}
