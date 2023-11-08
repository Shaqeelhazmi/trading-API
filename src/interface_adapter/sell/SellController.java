package interface_adapter.sell;

import entity.Stock;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

public class SellController {
    final SellInputBoundary userSellUseCaseInteractor;

    public SellController(SellInputBoundary userSellUseCaseInteractor) {
        this.userSellUseCaseInteractor = userSellUseCaseInteractor;
    }

    public void sell(Stock stock, int amount){
        SellInputData sellInputData = new SellInputData(stock, amount);
        userSellUseCaseInteractor.sell(sellInputData);
    }
}
