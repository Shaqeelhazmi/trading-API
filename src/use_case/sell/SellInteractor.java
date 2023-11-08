package use_case.sell;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary userPresenter;
    CommonUser commonUser;
    Stock stock;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface,
                          CommonUser commonUser, SellOutputBoundary sellOutputBoundary, Stock stock){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.userPresenter = sellOutputBoundary;
        this.commonUser = commonUser;
        this.stock = stock;
    }

    @Override
    public void sell(SellInputData sellInputData){
        if(!sellDataAccessObject.existsByName(sellInputData.getStockname())){
            userPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");
        }
        else{
            LocalDateTime now = LocalDateTime.now();
            Portfolio portfolio = commonUser.getPortfolio();

            if(portfolio.getPortfolio().get(stock) >= sellInputData.getAmount()) {

                SellOutputData sellOutputData = new SellOutputData(stock.getStockName(), now.toString(), false);
                userPresenter.prepareSuccessView(sellOutputData);
            } else {
                userPresenter.prepareNotEnough("You do not own enough of the stock to sell this amount.");
            }
        }
    }
}
