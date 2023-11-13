package use_case.signup;

import entity.Portfolio;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SignupInteractor implements SignupInputBoundary {

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
                            SignupOutputBoundary userPresenter,
                            UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;

    }
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now, new ArrayList<>(), new Portfolio());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
