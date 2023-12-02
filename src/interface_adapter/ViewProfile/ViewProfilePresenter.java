package interface_adapter.ViewProfile;

import interface_adapter.ViewManagerModel;
import use_case.ViewProfile.ViewProfileOutputData;
import use_case.ViewProfile.ViewProfileOutputBoundary;
import interface_adapter.Menu.MenuViewModel;

import javax.swing.*;

public class ViewProfilePresenter implements ViewProfileOutputBoundary {

    private ViewProfileViewModel viewProfileViewModel;
    private ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    public ViewProfilePresenter(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel, MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewProfileViewModel = viewProfileViewModel;
        this.menuViewModel = menuViewModel;
    }


    @Override
    public void prepareSuccessView(ViewProfileOutputData String) {
        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, "Fail to view profile.", "Fail", JOptionPane.INFORMATION_MESSAGE);
    }
}
