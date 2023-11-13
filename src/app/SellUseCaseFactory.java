package app;



import use_case.sell.SellDataAccessInterface;

import javax.swing.*;
import java.io.IOException;

public class SellUseCaseFactory {
    public static SellView create(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SellDataAccessInterface userDataAccessObject) {
        try {
            SellController sellController =  createSellUseCase(viewManagerModel, sellViewModel, userDataAccessObject);
            return new SellView(sellController, sellViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file");
        }
        return null;
    }

    private static SellController createSellUseCase(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SellDataAccessInterface userDataAccessObject) throws IOException{
        SellOutputBoundary sellOutputBoundary = new SellPresenter(viewManagerModel, sellViewModel);
        SellInputBoundary userSellInteractor = new SellInteractor(userDataAccessObject, sellOutputBoundary);

        return new SellController(userSellInteractor);
    }
}
