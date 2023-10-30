package use_case.sell;

import entity.CommonUser;

import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary sellPresenter;
    CommonUser commonUser;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface,
                          SellOutputBoundary sellOutputBoundary,  CommonUser commonUser){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.sellPresenter = sellOutputBoundary;
        this.commonUser = commonUser;
    }

    @Override
    public void sell(SellInputData sellInputData){
        String stockName = sellInputData.getStockname();
        int amount = sellInputData.getAmount();
        if(!sellDataAccessObject.existsByName(stockName)){
            sellPresenter.prepareFailView("Stock Not available: Wrong symbol used for Stock");
        }
        else{
            LocalDateTime now = LocalDateTime.now();

            SellOutputData sellOutputData = new SellOutputData(stockName, amount, now,false);
            sellPresenter.prepareSuccessView(sellOutputData);
        }
    }
}
