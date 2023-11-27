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

            LocalDateTime now = LocalDateTime.now();

            Portfolio portfolio = commonUser.getPortfolio();
            // If the account has enough money to buy, then they can buy
            double amount_used_for_purchase = buyInputData.getAmount() * stock.getPriceHistory().
                    getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));
            if (portfolio.getAccountBalance() >= amount_used_for_purchase) {
                buyDataAccessObject.buy(buyInputData.getAmount(), stock, commonUser, amount_used_for_purchase);

                BuyOutputData buyOutputData = new BuyOutputData(stock.getStockName(), now.toString());
                buyPresenter.prepareSuccessView(buyOutputData);
            } else {
                int affordable_amount = buyInputData.getAmount();
                double price_for_stock = stock.getPriceHistory().getDailyPriceHistory().
                        get(String.valueOf(now.getDayOfMonth()));
                double new_purchase_amount = affordable_amount * price_for_stock;
                while (portfolio.getAccountBalance() - new_purchase_amount <= 0) {
                    affordable_amount--;
                    new_purchase_amount = affordable_amount * stock.getPriceHistory().
                            getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));
                }
                buyPresenter.prepareFailView("You do not have enough non-liquid balance to make this purchase, you " +
                        "can only afford " + affordable_amount + " stocks");
        }
    }

}
