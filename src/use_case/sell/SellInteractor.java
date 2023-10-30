package use_case.sell;

import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary sellPresenter;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface,
                          SellOutputBoundary sellOutputBoundary){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.sellPresenter = sellOutputBoundary;
    }

    @Override
    public void sell(SellInputData sellInputData){
        String stockName = sellInputData.getStockname();
        int amount = sellInputData.getAmount();
        if(!sellDataAccessObject.stockExists(stockName)){
            sellPresenter.prepareFailView("Stock Not available: Wrong symbol used for Stock");
        }
        else{
            LocalDateTime now = LocalDateTime.now();


            SellOutputData sellOutputData = new SellOutputData(stockName, amount, false);
            sellPresenter.prepareSuccessView(sellOutputData);
        }
    }
}
