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

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("YouChat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChannelViewModel channelviewModel = new ChannelViewModel();

        TranslateViewModel translateViewModel = new TranslateViewModel();

        TranslateOutputBoundary translateOutputBoundary = new TranslatePresenter(channelviewModel, translateViewModel);

        TranslateInputBoundary translateInputBoundary = new TranslateInteractor(translateOutputBoundary, translateViewModel);

        TranslateController translateController = new TranslateController(translateInputBoundary);


        ChannelView channelView = new ChannelView(channelviewModel, translateViewModel, translateController);

        frame.getContentPane().add(channelView);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
