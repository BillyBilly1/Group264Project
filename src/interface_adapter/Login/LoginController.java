package interface_adapter.Login;

import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String user_id, String nickname) {
        LoginInputData loginInputData = new LoginInputData(user_id, nickname);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
