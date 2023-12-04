package use_case.login;

import entity.CommonUser;
import entity.Portfolio;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                CommonUser user = userDataAccessObject.get(loginInputData.getUsername());
                Portfolio portfolio = user.getPortfolio();
                double account_balance = portfolio.getAccountBalance();
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false, account_balance );
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}