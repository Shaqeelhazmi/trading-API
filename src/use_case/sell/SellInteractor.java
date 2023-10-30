package use_case.sell;

import entity.CommonAccount;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary sellPresenter;
    CommonAccount commonAccount;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface,
                          SellOutputBoundary sellOutputBoundary, CommonAccount commonAccount){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.sellPresenter = sellOutputBoundary;
        this.commonAccount = commonAccount;
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
