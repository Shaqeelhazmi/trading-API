package use_case.sell;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryStockDataAccessObject;
import entity.CommonUser;
import entity.Portfolio;
import entity.Stock;
import entity.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class SellInteractor implements SellInputBoundary{
    final SellDataAccessInterface sellDataAccessObject;
    final SellOutputBoundary sellPresenter;
    FileUserDataAccessObject userDataAccessObject;

    public SellInteractor(SellDataAccessInterface sellDataAccessInterface, SellOutputBoundary sellPresenter,
                          FileUserDataAccessObject userDataAccessObject){
        this.sellDataAccessObject = sellDataAccessInterface;
        this.sellPresenter = sellPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void sell(SellInputData sellInputData) throws IOException {

        InMemoryStockDataAccessObject stockDataAccessObject = new InMemoryStockDataAccessObject();
        CommonUser commonUser = userDataAccessObject.get(sellInputData.getUserName());
        Stock stock = stockDataAccessObject.getStockObject(sellInputData.getStockSymbol());


        LocalDateTime now = LocalDateTime.now();
        Portfolio portfolio = commonUser.getPortfolio();

        int amount = sellInputData.getAmount();
        double amount_received = amount * stock.getPriceHistory().
                getDailyPriceHistory().get(String.valueOf(now.getDayOfMonth()));

        if (amount <= portfolio.getPortfolio().get(stock.getStockSymbol())){

            if (amount < portfolio.getPortfolio().get(stock.getStockSymbol())){

                int old_value = commonUser.getPortfolio().getPortfolio().get(stock.getStockSymbol());
                int new_value = old_value - amount;
                portfolio.getPortfolio().replace(stock.getStockSymbol(), old_value, new_value);

            } else {
                portfolio.getPortfolio().remove(stock.getStockSymbol());
            }

            double current_balance_portfolio = portfolio.getAccountBalance();
            portfolio.setAccountBalance(current_balance_portfolio + amount_received);

            Transaction transaction = new Transaction(LocalDateTime.now(), stock.getStockName(), "Sell",
                    (amount_received/amount), amount);
            commonUser.getTransactionHistory().add(transaction);

            userDataAccessObject.save(commonUser);

            SellOutputData sellOutputData = new SellOutputData(stock.getStockName(), now.toString());
            sellPresenter.prepareSuccessView(sellOutputData);

        } else {
            sellPresenter.prepareFailView("You do not own enough of the stock to sell this amount.");
        }
    }
}
