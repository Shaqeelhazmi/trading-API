package use_case.sell;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary sellPresenter;
    CommonUser commonUser;
    Stock stock;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface,
                          CommonUser commonUser, SellOutputBoundary sellOutputBoundary, Stock stock){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.sellPresenter = sellOutputBoundary;
        this.commonUser = commonUser;
        this.stock = stock;
    }

    @Override
    public void sell(SellInputData sellInputData){
        LocalDateTime now = LocalDateTime.now();
        Portfolio portfolio = commonUser.getPortfolio();

        double amount_received = sellInputData.getAmount() * stock.getPriceHistory().
                getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));

        if (sellInputData.getAmount() <= portfolio.getPortfolio().get(stock.getStockSymbol())){
            sellDataAccessObject.sell(sellInputData.getAmount(), stock, commonUser, amount_received);

            SellOutputData sellOutputData = new SellOutputData(stock.getStockName(), now.toString());
            sellPresenter.prepareSuccessView(sellOutputData);
        } else {
            sellPresenter.prepareFailView("You do not own enough of the stock to sell this amount.");
        }
    }
}
