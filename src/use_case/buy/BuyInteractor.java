package use_case.buy;

import entity.CommonUser;
import entity.Portfolio;

import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary userPresenter;

    CommonUser commonUser;



    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface, CommonUser commonUser,
                         BuyOutputBoundary buyOutputBoundary) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.userPresenter = buyOutputBoundary;
        this.commonUser = commonUser;
    }

    @Override
    public void buy(BuyInputData buyInputData) {
        if (!buyDataAccessObject.existsByName(buyInputData.getStockname()))
            userPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");
        else {
            LocalDateTime now = LocalDateTime.now();
            Portfolio portfolio = commonUser.getPortfolio();
            buyDataAccessObject.buy(portfolio);
            
            BuyOutputData buyOutputData =  new BuyOutputData();
            userPresenter.prepareSuccessView(buyOutputData);

        }
    }
}
