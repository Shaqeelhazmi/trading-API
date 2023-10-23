package app;

import use_case.buy.BuyDataAccessInterface;
import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInteractor;
import use_case.buy.BuyOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class BuyUseCaseFactory {
    public static BuyView create(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject) {
    try {
        BuyController buyController =  createBuyUseCase(viewManagerModel, buyViewModel, userDataAccessObject);
        return new BuyView(buyController, buyViewModel);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Could not open file");
    }
        return null;
    }

    private static BuyController createBuyUseCase(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, BuyDataAccessInterface userDataAccessObject) throws IOException{
        BuyOutputBoundary buyOutputBoundary = new BuyPresenter(viewManagerModel, buyViewModel);
        UserFactory userFactory = new CommonUserFactory();
        BuyInputBoundary userBuyInteractor = new BuyInteractor(userDataAccessObject, buyOutputBoundary);

        return new BuyController(userBuyInteractor);
    }
}
