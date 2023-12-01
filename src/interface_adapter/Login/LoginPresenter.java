package interface_adapter.Login;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;
import interface_adapter.logged_in.LoggedInViewModel;
import view.MenuView;

import java.awt.*;


public class   LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;

    private final LoggedInViewModel loggedInViewModel;

    private final MenuViewModel menuViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          LoggedInViewModel loggedInViewModel, MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.menuViewModel = menuViewModel;
    }


    @Override
    public void prepareSuccessView(LoginOutputData user) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUser_id(user.getUser_id());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(menuViewModel.getViewName());


        System.out.println(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUser_idError(error);
        loginViewModel.firePropertyChanged();
    }
}
