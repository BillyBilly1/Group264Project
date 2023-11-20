package use_case.Login;

import entity.User.UserFactory;
import entity.User.User;

public class LoginInteractor implements LoginInputBoundary{

    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;
    final UserFactory userFactory;

    public LoginInteractor(LoginDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(LoginInputData loginInputData) {
        String user_id = loginInputData.getUser_id();
        String nickname = loginInputData.getNickname();
        if (! userDataAccessObject.existsByName(user_id)) {
            loginPresenter.prepareFailView(user_id + ": Account does not exist.");
        } else {
            User user = userDataAccessObject.get(loginInputData.getUser_id());

            LoginOutputData loginOutputData = new LoginOutputData(user.getUser_Id(), user.getNickName(), false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}
