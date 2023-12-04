package use_case.Login;

import entity.User.UserFactory;
import entity.User.User;

import java.util.Objects;

public class LoginInteractor implements LoginInputBoundary{

    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }


    @Override
    public void execute(LoginInputData loginInputData) {
        String user_id = loginInputData.getUser_id();
        String nickname = loginInputData.getNickname();
        if (userDataAccessObject.get_username(user_id) == null) {
            loginPresenter.prepareFailView(user_id + ": Account does not exist.");
        }  else if (! Objects.equals(userDataAccessObject.get_username(user_id), nickname)){
            loginPresenter.prepareFailView("User ID and password don't match, please try again");
        }
        else {
            LoginOutputData loginOutputData = new LoginOutputData(user_id,
                    nickname, false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}
