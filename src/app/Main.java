package app;

import data_access.FileUserDataAccessObject;
import data_access.StockDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.stock.StockViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("The Wolf of Bay Street");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setBackground(new Color(255, 87, 51));

        CardLayout cardLayout = new CardLayout();
        JFrame aFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
        application.setPreferredSize(screenSize);


        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        BuyViewModel buyViewModel = new BuyViewModel();
        SellViewModel sellViewModel = new SellViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        PortfolioViewModel portfolioViewModel = new PortfolioViewModel();
        StockViewModel stockViewModel = new StockViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StockDataAccessObject stockDataAccessObject;
        try{
            stockDataAccessObject = new StockDataAccessObject("./stocks.json");
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, signupViewModel, searchViewModel, portfolioViewModel);
        views.add(loggedInView, loggedInView.viewName);

        BuyView buyView = BuyUseCaseFactory.create(viewManagerModel, buyViewModel, stockDataAccessObject,
                loggedInViewModel, searchViewModel, userDataAccessObject, stockViewModel);
        views.add(buyView, buyView.viewName);

        SellView sellView = SellUseCaseFactory.create(viewManagerModel, sellViewModel, stockDataAccessObject,
                loggedInViewModel, searchViewModel, userDataAccessObject, stockViewModel);
        views.add(sellView, sellView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, stockViewModel);
        views.add(searchView, searchView.viewName);

        PortfolioView portfolioView = PortfolioUseCaseFactory.create(viewManagerModel, portfolioViewModel, loggedInViewModel, userDataAccessObject);
        views.add(portfolioView, portfolioView.viewName);

        StockView stockView = new StockView(stockViewModel, buyViewModel, sellViewModel, loggedInViewModel, viewManagerModel, searchViewModel);
        views.add(stockView, stockView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}