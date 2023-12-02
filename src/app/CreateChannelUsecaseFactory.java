package app;

import entity.Channel.CommonChannelFactory;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelPresenter;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelViewModel;
import use_case.CreateChannel.CreateChannelDataAccessInterface;
import use_case.CreateChannel.CreateChannelInputBoundary;
import use_case.CreateChannel.CreateChannelInteractor;
import use_case.CreateChannel.CreateChannelOutputBoundary;
import view.CreateChannelView;

public class CreateChannelUsecaseFactory {


    public static CreateChannelView create(ViewManagerModel viewManagerModel,
                                    MenuViewModel menuViewModel, CreateChannelViewModel createChannelViewModel,
                                    CreateChannelDataAccessInterface fileChannelDataAccessObject) {
        CreateChannelController createChannelController = createCreateChannelController(viewManagerModel,
                createChannelViewModel, menuViewModel, fileChannelDataAccessObject);
        return new CreateChannelView(createChannelViewModel, createChannelController);
    }

    public static CreateChannelController createCreateChannelController(
            ViewManagerModel viewManagerModel, CreateChannelViewModel createChannelViewModel,
            MenuViewModel menuViewModel, CreateChannelDataAccessInterface fileChannelDataAccessObject) {
        CreateChannelOutputBoundary createChannelOutputBoundary =
                new CreateChannelPresenter(viewManagerModel, createChannelViewModel, menuViewModel);
        CreateChannelInputBoundary createChannelInputBoundary =
                new CreateChannelInteractor(fileChannelDataAccessObject,
                        createChannelOutputBoundary, new CommonChannelFactory());
        return new CreateChannelController(createChannelInputBoundary);
    }

}
