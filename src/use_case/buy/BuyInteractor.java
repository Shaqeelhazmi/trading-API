package use_case.buy;

import entity.CommonAccount;

import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary userPresenter;


    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface,
                         BuyOutputBoundary buyOutputBoundary) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.userPresenter = buyOutputBoundary;
    }

    @Override
    public void buy(BuyInputData buyInputData) {
        if (!buyDataAccessObject.existsByName(buyInputData.getStockname()))
            userPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");
        else {
            LocalDateTime now = LocalDateTime.now();
            BuyOutputData buyOutputData =  new BuyOutputData();
            userPresenter.prepareSuccessView(buyOutputData);

        }
    }
}
