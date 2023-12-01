package use_case.buy;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryStockDataAccessObject;
//import data_access.StockDataAccessObject;
import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;
import entity.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class BuyInteractor implements BuyInputBoundary{
    BuyDataAccessInterface buyDataAccessObject;
    BuyOutputBoundary buyPresenter;
    FileUserDataAccessObject userDataAccessObject;


    public BuyInteractor(BuyDataAccessInterface buyDataAccessInterface, BuyOutputBoundary buyPresenter,
                         FileUserDataAccessObject userDataAccessObject) {
        this.buyDataAccessObject = buyDataAccessInterface;
        this.buyPresenter = buyPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void buy(BuyInputData buyInputData) throws IOException {

        //FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject("./users.json");
        //StockDataAccessObject stockDataAccessObject = new StockDataAccessObject("./stocks.json");
        InMemoryStockDataAccessObject stockDataAccessObject = new InMemoryStockDataAccessObject();


        //CommonUser commonUser = fileUserDataAccessObject.get(buyInputData.getUserName());
        CommonUser commonUser = userDataAccessObject.get(buyInputData.getUserName());
        Stock stock = stockDataAccessObject.getStockObject(buyInputData.getStockSymbol());


        LocalDateTime now = LocalDateTime.now();
        Portfolio portfolio = commonUser.getPortfolio();

        // If the account has enough money to buy, then they can buy
        int amount = buyInputData.getAmount();
        double amount_used_for_purchase = amount * stock.getPriceHistory().getDailyPriceHistory().
                get(String.valueOf(now.getDayOfMonth()));

        if (portfolio.getAccountBalance() >= amount_used_for_purchase) {

            if (portfolio.getPortfolio().containsKey(stock.getStockSymbol())) {
                int old_value = portfolio.getPortfolio().get(stock.getStockSymbol());
                int new_value = old_value + amount;
                portfolio.getPortfolio().replace(stock.getStockSymbol(), old_value, new_value);
            } else {
                portfolio.getPortfolio().put(stock.getStockName(), amount);
            }
            // Get the amount of money you have in portfolio
            double current_balance_portfolio = commonUser.getPortfolio().getAccountBalance();

            // Updating the amount left in account
            portfolio.setAccountBalance(current_balance_portfolio - amount_used_for_purchase);

            //Updating transaction history
            Transaction transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Buy",
                    (amount_used_for_purchase / amount), amount);
            commonUser.getTransactionHistory().add(transaction);

            //Save the new information
            userDataAccessObject.save(commonUser);

            BuyOutputData buyOutputData = new BuyOutputData(stock.getStockName(), now.toString());
            buyPresenter.prepareSuccessView(buyOutputData);
        } else {
                double price_for_stock = stock.getPriceHistory().getDailyPriceHistory().
                        get(String.valueOf(now.getDayOfMonth()));
                double new_purchase_amount = amount * price_for_stock;
                while (portfolio.getAccountBalance() - new_purchase_amount <= 0) {
                    amount--;
                    new_purchase_amount = amount * stock.getPriceHistory().
                            getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));
                }
                buyPresenter.prepareFailView("You do not have enough non-liquid balance to make this purchase, you " +
                        "can only afford " + amount + " stocks");
        }
    }

}
