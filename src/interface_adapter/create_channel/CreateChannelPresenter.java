package interface_adapter.create_channel;


import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateChannel.CreateChannelOutputBoundary;
import use_case.CreateChannel.CreateChannelOutputData;
import view.CreateChannelView;

public class CreateChannelPresenter implements CreateChannelOutputBoundary {

    private final CreateChannelViewModel createChannelViewModel;
    private final ViewManagerModel viewManagerModel;
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

    }

    public void prepareFailView(String error) {

    }

}
