package use_case.buy;

import data_access.FileUserDataAccessObject;
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

        CommonUser commonUser = userDataAccessObject.get(buyInputData.getUserName());
        Stock stock = buyDataAccessObject.getStock(buyInputData.getStockSymbol());

        LocalDateTime now = LocalDateTime.now();
        Portfolio portfolio = commonUser.getPortfolio();

        // If the account has enough money to buy, then they can buy
        int amount = buyInputData.getAmount();
        String date = now.toLocalDate().toString();
        double amount_used_for_purchase = 0;
        double price_for_stock = 0;

        int i = 0;
        //Check if date is in the database
        while(!stock.getPriceHistory().getDailyPriceHistory().containsKey(date)) {
            i++;
            date = now.toLocalDate().minusDays(i).toString();
        }

        if (stock.getPriceHistory().getDailyPriceHistory().containsKey(date)){
            price_for_stock = stock.getPriceHistory().getDailyPriceHistory().
                    get(date);
            amount_used_for_purchase = amount * price_for_stock;
        }

        if (portfolio.getAccountBalance() >= amount_used_for_purchase) {
            if (portfolio.getPortfolio().containsKey(stock.getStockSymbol())) {
                int old_value = portfolio.getPortfolio().get(stock.getStockSymbol());
                int new_value = old_value + amount;
                portfolio.getPortfolio().replace(stock.getStockSymbol(), old_value, new_value);
            } else {
                portfolio.getPortfolio().put(stock.getStockSymbol(), amount);
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

            BuyOutputData buyOutputData = new BuyOutputData(stock.getStockName(), now.toString(), buyInputData.getAmount());
            buyPresenter.prepareSuccessView(buyOutputData);
        } else{
                amount = (int) Math.round(portfolio.getAccountBalance() / price_for_stock);
                buyPresenter.prepareFailView("You do not have enough non-liquid balance to make this purchase, you " +
                    "can only afford " + amount + " stocks");
                }
        }
    }
