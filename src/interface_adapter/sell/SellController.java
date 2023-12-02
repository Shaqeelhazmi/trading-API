package interface_adapter.sell;

import entity.Stock;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

import java.io.IOException;

public class SellController {
    final SellInputBoundary userSellUseCaseInteractor;

    public SellController(SellInputBoundary userSellUseCaseInteractor) {
        this.userSellUseCaseInteractor = userSellUseCaseInteractor;
    }

    public void sell(String stockSymbol, int amount, String userName) throws IOException {
        SellInputData sellInputData = new SellInputData(stockSymbol, amount, userName);
        userSellUseCaseInteractor.sell(sellInputData);
    }
}
