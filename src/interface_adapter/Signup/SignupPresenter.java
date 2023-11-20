package interface_adapter.Signup;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupOutputdata;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;

    public SignupPresenter(SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputdata user) {
        // On success, switch to the login view.
        LoginState loginState = loginViewModel.getState();
        loginState.setUser_id(user.getUser_id());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUser_idError(error);
        signupViewModel.firePropertyChanged();
    }
}
