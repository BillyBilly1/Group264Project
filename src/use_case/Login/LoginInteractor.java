package use_case.Login;

import entity.User.UserFactory;
import entity.User.User;

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
        }  else if (userDataAccessObject.get_username(user_id) != nickname){
            loginPresenter.prepareFailView("User id and nick name does not match.");
        }
        else {
            LoginOutputData loginOutputData = new LoginOutputData(userDataAccessObject.get_username(loginInputData.getUser_id()),
                    nickname, false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}
