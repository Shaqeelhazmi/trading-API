package use_case.buy;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary userPresenter;

    CommonUser commonUser;

    Stock stock;


    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface, CommonUser commonUser,
                         BuyOutputBoundary buyOutputBoundary, Stock stock) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.userPresenter = buyOutputBoundary;
        this.commonUser = commonUser;
        this.stock = stock;

    }

    @Override
    public void buy(BuyInputData buyInputData) {
        if (!buyDataAccessObject.existsByName(buyInputData.getStockname()))
            userPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");
        else {
            LocalDateTime now = LocalDateTime.now();

            Portfolio portfolio = commonUser.getPortfolio();
            buyDataAccessObject.buy(portfolio, buyInputData.getAmount(), stock);

            Portfolio portfolio = commonUser.getPortfolio();
            buyDataAccessObject.buy(portfolio);

            BuyOutputData buyOutputData =  new BuyOutputData();
            userPresenter.prepareSuccessView(buyOutputData);

        }
    }
}
