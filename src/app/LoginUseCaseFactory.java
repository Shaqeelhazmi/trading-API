package app;

import entity.CommonUserFactory;
import entity.Portfolio;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioPresenter;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import use_case.portfolio.PortfolioDataAccessInterface;
import use_case.portfolio.PortfolioInputBoundary;
import use_case.portfolio.PortfolioInteractor;
import use_case.portfolio.PortfolioOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel, PortfolioViewModel portfolioViewModel, PortfolioDataAccessInterface portfolioDataAccessInterface) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            PortfolioController portfolioController =  createPortfolioUseCase(viewManagerModel, portfolioViewModel, portfolioDataAccessInterface);
            return new LoginView(loginViewModel, loginController, viewManagerModel, signupViewModel, portfolioController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }

    private static PortfolioController createPortfolioUseCase(ViewManagerModel viewManagerModel, PortfolioViewModel portfolioViewModel,
                                                              PortfolioDataAccessInterface portfolioDataAccessObject
                                                              ) throws IOException{
        PortfolioOutputBoundary portfolioOutputBoundary = new PortfolioPresenter(viewManagerModel, portfolioViewModel);
        PortfolioInputBoundary portfolioInteractor = new PortfolioInteractor(portfolioDataAccessObject, portfolioOutputBoundary);

        return new PortfolioController(portfolioInteractor);
    }

}
