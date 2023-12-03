package interface_adapter.create_channel;


import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateChannel.CreateChannelOutputBoundary;
import use_case.CreateChannel.CreateChannelOutputData;
import view.CreateChannelView;

import javax.swing.*;

public class CreateChannelPresenter implements CreateChannelOutputBoundary {

    private CreateChannelViewModel createChannelViewModel;
    private ViewManagerModel viewManagerModel;

    private final MenuViewModel menuViewModel;


    public CreateChannelPresenter(ViewManagerModel viewManagerModel,
                                  CreateChannelViewModel createChannelViewModel,
                                  MenuViewModel menuViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.createChannelViewModel = createChannelViewModel;
        this.menuViewModel = menuViewModel;
    }


    @Override
    public void prepareSuccessView(CreateChannelOutputData outputData) {
        menuViewModel.setChannelList(outputData.getChannel());
        createChannelViewModel.setSuccessMessage("Channel '" + outputData.getChannel().getName() + "' created successfully.");
        String successMessage = createChannelViewModel.getSuccessMessage();
        JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
        createChannelViewModel.setSuccessMessage("");


        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null,
                "channel name or channel url already exists", "Fail", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void back() {
        viewManagerModel.setActiveView("menu");
        viewManagerModel.firePropertyChanged();
    }

}
