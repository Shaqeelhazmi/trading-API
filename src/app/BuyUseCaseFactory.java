package app;

import buy.BuyController;
import buy.BuyPresenter;
import buy.BuyViewModel;
import entity.CommonAccount;
import entity.CommonUser;
import entity.Stock;
import use_case.buy.BuyDataAccessInterface;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInteractor;
import use_case.buy.BuyOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class BuyUseCaseFactory {
    public static BuyView create(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject, CommonAccount account, Stock stock) {
    try {
        BuyController buyController =  createBuyUseCase(viewManagerModel, buyViewModel, userDataAccessObject, account, stock);
        return new BuyView(buyController, buyViewModel);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Could not open file");
    }
        return null;
    }

    private static BuyController createBuyUseCase(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject, CommonUser account, Stock stock) throws IOException{
        BuyOutputBoundary buyOutputBoundary = new BuyPresenter(viewManagerModel, buyViewModel);
        UserFactory userFactory = new CommonUserFactory();
        BuyInputBoundary userBuyInteractor = new BuyInteractor(userDataAccessObject, account, buyOutputBoundary, stock);

        return new BuyController(userBuyInteractor);
    }
}
