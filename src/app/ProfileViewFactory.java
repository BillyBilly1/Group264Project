package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewProfile.ViewProfileController;
import interface_adapter.ViewProfile.ViewProfilePresenter;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import use_case.ViewProfile.ViewProfileDataAccessInterface;
import use_case.ViewProfile.ViewProfileInputBoundary;
import use_case.ViewProfile.ViewProfileInteractor;
import use_case.ViewProfile.ViewProfileOutputBoundary;
import view.SelfProfileView;

public class ProfileViewFactory {

    public static SelfProfileView create(ViewManagerModel viewManagerModel,
                                         MenuViewModel menuViewModel, ViewProfileViewModel viewProfileViewModel,
                                         ViewProfileDataAccessInterface fileProfileDataAccessObject) {

        ViewProfileController viewProfileController = createViewProfileController(viewManagerModel, viewProfileViewModel, menuViewModel, fileProfileDataAccessObject);

        return new SelfProfileView(viewManagerModel, viewProfileViewModel);
    }

    public static ViewProfileController createViewProfileController(
            ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel,
            MenuViewModel menuViewModel, ViewProfileDataAccessInterface fileProfileDataAccessObject) {

        ViewProfileOutputBoundary viewProfileOutputBoundary = new ViewProfilePresenter(viewManagerModel, viewProfileViewModel, menuViewModel);
        ViewProfileInputBoundary viewProfileInputBoundary = new ViewProfileInteractor(fileProfileDataAccessObject, viewProfileOutputBoundary);
        return new ViewProfileController(viewProfileInputBoundary);
    }
}
