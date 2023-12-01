package app;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupViewModel;
import use_case.Signup.SignupDataAccessInterface;
import entity.User.CommonUserFactory;
import interface_adapter.*;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignupUsecaseFactory {

    /** Prevent instantiation. */
    private SignupUsecaseFactory() {}

    public static SignUpView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupDataAccessInterface userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignUpView(signupViewModel, signupController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }


    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                                                            LoginViewModel loginViewModel,
                                                            SignupDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupViewModel, loginViewModel, viewManagerModel);

        CommonUserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
