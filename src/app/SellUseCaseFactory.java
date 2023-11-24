//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.sell.SellController;
//import interface_adapter.sell.SellPresenter;
//import interface_adapter.sell.SellViewModel;
//import entity.CommonUser;
//import entity.Stock;
//import use_case.sell.SellDataAccessInterface;
//import use_case.sell.SellInputBoundary;
//import use_case.sell.SellInteractor;
//import use_case.sell.SellOutputBoundary;
//import view.SellView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class SellUseCaseFactory {
//
//    /** Prevent instantiation. */
//    private SellUseCaseFactory() {}
//    public static SellView create(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SellDataAccessInterface userDataAccessObject, CommonUser commonUser, Stock stock) {
//        try {
//            SellController sellController =  createSellUseCase(viewManagerModel, sellViewModel, userDataAccessObject, commonUser, stock);
//            return new SellView(sellController, sellViewModel);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open file");
//        }
//        return null;
//    }
//
//    private static SellController createSellUseCase(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SellDataAccessInterface userDataAccessObject, CommonUser commonUser, Stock stock) throws IOException{
//        SellOutputBoundary sellOutputBoundary = new SellPresenter(viewManagerModel, sellViewModel);
//        SellInputBoundary userSellInteractor = new SellInteractor(userDataAccessObject, commonUser, sellOutputBoundary, stock);
//
//        return new SellController(userSellInteractor);
//    }
//}
