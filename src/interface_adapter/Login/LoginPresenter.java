package interface_adapter.Login;

import interface_adapter.logged_in.LoggedInState;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;
import interface_adapter.logged_in.LoggedInViewModel;


public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;

    private final LoggedInViewModel loggedInViewModel;

    public LoginPresenter(LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel) {
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(LoginOutputData user) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUser_id(user.getUser_id());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUser_idError(error);
        loginViewModel.firePropertyChanged();
    }
}
