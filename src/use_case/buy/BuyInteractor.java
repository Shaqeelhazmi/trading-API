package use_case.buy;

import entity.CommonAccount;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary userPresenter;

    CommonAccount commonAccount;



    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface, CommonAccount commonAccount,
                         BuyOutputBoundary buyOutputBoundary) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.userPresenter = buyOutputBoundary;
        this.commonAccount = commonAccount;

    }

    @Override
    public void buy(BuyInputData buyInputData) {
        if (!buyDataAccessObject.existsByName(buyInputData.getStockname()))
            userPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");
        else {
            LocalDateTime now = LocalDateTime.now();
            Portfolio portfolio = commonAccount.getPortfolio();
            buyDataAccessObject.buy(portfolio);
            
            BuyOutputData buyOutputData =  new BuyOutputData();
            userPresenter.prepareSuccessView(buyOutputData);

        }
    }
}
