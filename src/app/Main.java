package app;

import data_access.FileChannelDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.User.UserFactory;
import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.translation.TranslateController;
import interface_adapter.translation.TranslatePresenter;
import interface_adapter.translation.TranslateViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Login.LoginViewModel;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import view.ChannelView;
import view.SignUpView;
import view.LoginView;
import view.ViewManager;
import app.LoginUsecaseFactory;
import app.SignupUsecaseFactory;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignUpView signupView = SignupUsecaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, "sign up");

        LoginView loginView = LoginUsecaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, "log in");

        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

}