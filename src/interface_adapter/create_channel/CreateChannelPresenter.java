package interface_adapter.create_channel;


import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateChannel.CreateChannelOutputBoundary;
import use_case.CreateChannel.CreateChannelOutputData;
import view.CreateChannelView;

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
        System.out.println("成功");
        menuViewModel.setChannelList(outputData.getChannel());
        createChannelViewModel.setSuccessMessage("Channel '" + outputData.getChannel().getChannelName() + "' created successfully.");


        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareFailView(String error) {
        System.out.println("失败");

    }

}
