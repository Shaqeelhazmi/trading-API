package use_case.buy;

import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;
import entity.Transaction;

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
        int amount = buyInputData.getAmount();
        double amount_used_for_purchase = amount * stock.getPriceHistory().
                    getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));

        if (portfolio.getAccountBalance() >= amount_used_for_purchase) {
            if (commonUser.getPortfolio().getPortfolio().containsKey(stock.getStockSymbol())) {
                int old_value = commonUser.getPortfolio().getPortfolio().get(stock.getStockSymbol());
                int new_value = old_value + amount;
                commonUser.getPortfolio().getPortfolio().replace(stock.getStockSymbol(), old_value, new_value);
            } else {
                commonUser.getPortfolio().getPortfolio().put(stock.getStockName(), amount);
            }
            // Get the amount of money you have in portfolio
            double current_balance_portfolio = commonUser.getPortfolio().getAccountBalance();

            // Updating the amount left in account
            commonUser.getPortfolio().setAccountBalance(current_balance_portfolio - amount_used_for_purchase);

            //Updating transaction history
            Transaction transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Buy",
                    (amount_used_for_purchase / amount), amount);
            commonUser.getTransactionHistory().add(transaction);
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
