package use_case.buy;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;

import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary buyPresenter;

    CommonUser commonUser;

    Stock stock;


    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface, CommonUser commonUser,
                         BuyOutputBoundary buyOutputBoundary, Stock stock) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.buyPresenter = buyOutputBoundary;
        this.commonUser = commonUser;
        this.stock = stock;

    }

    @Override
    public void buy(BuyInputData buyInputData) {
        if (!buyDataAccessObject.existsByName(buyInputData.getStockname()))
            buyPresenter.prepareNotAvailable("Stock Not available: Wrong symbol used for Stock");


        else {
            LocalDateTime now = LocalDateTime.now();

            Portfolio portfolio = commonUser.getPortfolio();
            // If the account has enough money to buy, then they can buy
            if (portfolio.getAccountBalance() >= amount_used_for_purchase(buyInputData.getAmount())) {
                buyDataAccessObject.buy(buyInputData.getAmount(), stock, commonUser);

                BuyOutputData buyOutputData = new BuyOutputData(stock.getStockName(), now.toString());
                buyPresenter.prepareSuccessView(buyOutputData);
            } else {
                buyPresenter.prepareNotEnough("You do not have enough non-liquid balance to make this purchase, you" +
                        "can only afford " + afford(portfolio, buyInputData.getAmount()) + "stocks");
            }
        }
    }

    // Helper functions
    public double amount_used_for_purchase(int amount) {
        return amount * stock.getPriceHistory().getDailyPriceHistory().
                get(stock.getStockSymbol());
    }

    public int afford(Portfolio portfolio, int amount) {
        while (portfolio.getAccountBalance() - amount_used_for_purchase(amount) <= 0) {
            amount--;
        }
        return amount;
    }
}
