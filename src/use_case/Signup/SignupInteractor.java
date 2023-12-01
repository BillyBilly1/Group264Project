package use_case.Signup;

import entity.User.CommonUserFactory;
import entity.User.User;
import entity.User.UserFactory;
import interface_adapter.Signup.SignupPresenter;


public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final CommonUserFactory userFactory;
    final String profileUrl = "";

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            CommonUserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.get_username(signupInputData.getUser_id()) != null) {
            userPresenter.prepareFailView("User already exists.");
        } else {

            User user = userFactory.create(signupInputData.getUser_id(), signupInputData.getNickname(), profileUrl);
            userDataAccessObject.save(user);

            SignupOutputdata signupOutputData = new SignupOutputdata(user.getUser_Id(), user.getNickName(), profileUrl , false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void skip() {
        userPresenter.skip();

    }
}
