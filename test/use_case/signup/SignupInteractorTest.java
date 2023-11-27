package use_case.signup;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @org.junit.jupiter.api.Test
    void execute() {
        FileUserDataAccessObject fudao;
        CommonUserFactory uf = new CommonUserFactory();
        try {
            fudao = new FileUserDataAccessObject("./users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        class TestPresenter implements SignupOutputBoundary {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                System.out.println(user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println(error);
            }
        }
        SignupInputData signupInputData = new SignupInputData("billy", "password", "password");
        SignupInteractor signupInteractor = new SignupInteractor(fudao, new TestPresenter(), uf);
        signupInteractor.execute(signupInputData);
    }
}