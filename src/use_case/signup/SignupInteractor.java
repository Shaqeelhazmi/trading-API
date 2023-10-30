package use_case.signup;

import entity.UserFactory;

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

    }

}
