package app;

import entity.CommonUser;
import entity.Stock;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.sell.*;
import data_access.StockDataAccessObject;
import view.SellView;
import use_case.sell.*;

import javax.swing.*;
import java.io.IOException;

public class SellUseCaseFactory {
    private SellUseCaseFactory() {}
    public static SellView create(ViewManagerModel viewManagerModel, SellViewModel sellViewModel,
                                  SellDataAccessInterface userDataAccessObject) {
        try {
            SellController sellController =  createSellUseCase(viewManagerModel, sellViewModel);
            return new SellView(sellController, sellViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file");
        }
        return null;
    }

    private static SellController createSellUseCase(ViewManagerModel viewManagerModel, SellViewModel sellViewModel) throws IOException{
        SellDataAccessInterface userDataAccessObject = new StockDataAccessObject();
        SellOutputBoundary sellOutputBoundary = new SellPresenter(viewManagerModel, sellViewModel);
        UserFactory userFactory = new CommonUserFactory();
        SellInputBoundary userSellInteractor = new SellInteractor(userDataAccessObject, sellOutputBoundary);

        return new SellController(userSellInteractor);
    }
}
