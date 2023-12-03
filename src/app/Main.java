package app;

import data_access.FileChannelDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.User.UserFactory;
import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.translation.TranslateController;
import interface_adapter.translation.TranslatePresenter;
import interface_adapter.translation.TranslateViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import use_case.ListChannel.ListChannelDataAccessInterface;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import use_case.ViewProfile.ViewProfileDataAccessInterface;
import view.*;
import app.LoginUsecaseFactory;
import app.SignupUsecaseFactory;
import app.ProfileViewFactory;
import view.SelfProfileView;



import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("You-Chat");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        application.setMinimumSize(new Dimension(500, 550));

        System.out.println();

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        MenuViewModel menuViewModel = new MenuViewModel();
        TranslateViewModel translateViewModel = new TranslateViewModel();
        ListChannelViewModel listChannelViewModel = new ListChannelViewModel();
        CreateChannelViewModel createChannelViewModel = new CreateChannelViewModel();
        ViewProfileViewModel viewProfileViewModel = new ViewProfileViewModel();




        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileChannelDataAccessObject fileChannelDataAccessObject;
        try {
            fileChannelDataAccessObject = new FileChannelDataAccessObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);

        }

        SignUpView signupView = SignupUsecaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, "sign up");

        LoginView loginView = LoginUsecaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, menuViewModel, userDataAccessObject);
        views.add(loginView, "log in");

        MenuView menuView = MenuViewFactory.create(viewManagerModel, menuViewModel, listChannelViewModel,createChannelViewModel, userDataAccessObject, viewProfileViewModel);
        views.add(menuView, "menu");

        CreateChannelView createChannelView =
                CreateChannelUsecaseFactory.create(viewManagerModel, menuViewModel, createChannelViewModel, fileChannelDataAccessObject);
        views.add(createChannelView, "create channel");

        SelfProfileView selfProfileView = ProfileViewFactory.create(viewManagerModel, menuViewModel, viewProfileViewModel, userDataAccessObject);
        views.add(selfProfileView, "profile");

        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

}