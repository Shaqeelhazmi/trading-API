package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.Portfolio;
import entity.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {


    @Test
    void successTest(){
        LoginInputData inputData = new LoginInputData("Bob", "123");
        LoginUserDataAccessInterface loginUserDataAccessInterface = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("Bob", "123", LocalDateTime.now(), new ArrayList<String>(),
                new Portfolio(new HashMap<>(), 10000), new ArrayList<Transaction>());
        loginUserDataAccessInterface.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Bob", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureAccountTest(){
        LoginInputData inputData = new LoginInputData("Bob", "123");
        LoginUserDataAccessInterface loginUserDataAccessInterface = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("Billy", "123", LocalDateTime.now(), new ArrayList<String>(),
                new Portfolio(new HashMap<>(), 10000), new ArrayList<Transaction>());
        loginUserDataAccessInterface.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Bob: Account does not exist.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordTest(){
        LoginInputData inputData = new LoginInputData("Bob", "1234");
        LoginUserDataAccessInterface loginUserDataAccessInterface = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("Bob", "123", LocalDateTime.now(), new ArrayList<String>(),
                new Portfolio(new HashMap<>(), 10000), new ArrayList<Transaction>());
        loginUserDataAccessInterface.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for Bob.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, successPresenter);
        interactor.execute(inputData);
    }
}
