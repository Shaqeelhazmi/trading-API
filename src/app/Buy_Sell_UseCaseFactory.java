package app;

import entity.CommonUser;
import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyPresenter;
import interface_adapter.buy.BuyViewModel;
import use_case.buy.BuyDataAccessInterface;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInteractor;
import use_case.buy.BuyOutputBoundary;
import view.Buy_Sell_View;

import javax.swing.*;
import java.io.IOException;

public class Buy_Sell_UseCaseFactory {
    public static Buy_Sell_View create(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject, CommonUser user, Stock stock, int amount) {
        try {
            BuyController buyController =  createBuyUseCase(viewManagerModel, buyViewModel, userDataAccessObject, user, stock);
            return new Buy_Sell_View(buyController, buyViewModel, stock.getStockName(), amount);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to create");
        }
        return null;
    }

    private static BuyController createBuyUseCase(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject, CommonUser account, Stock stock) throws IOException{
        BuyOutputBoundary buyOutputBoundary = new BuyPresenter(viewManagerModel, buyViewModel);
        BuyInputBoundary userBuyInteractor = new BuyInteractor(userDataAccessObject, account, buyOutputBoundary, stock);

        return new BuyController(userBuyInteractor);
    }
}
