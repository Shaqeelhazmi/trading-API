package interface_adapter.sell;

import entity.Stock;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

public class SellController {
    final SellInputBoundary userSellUseCaseInteractor;

    public SellController(SellInputBoundary userSellUseCaseInteractor) {
        this.userSellUseCaseInteractor = userSellUseCaseInteractor;
    }

    public void sell(String stockName, int amount){
        SellInputData sellInputData = new SellInputData(stockName, amount);
        userSellUseCaseInteractor.sell(sellInputData);
    }
}
