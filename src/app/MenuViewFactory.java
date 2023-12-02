package app;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelController;
import interface_adapter.list_Channel.ListChannelPresenter;
import interface_adapter.list_Channel.ListChannelViewModel;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import use_case.ListChannel.*;
import view.MenuView;

public class MenuViewFactory {

    public static MenuView create(ViewManagerModel viewManagerModel, MenuViewModel menuViewModel,
                           ListChannelViewModel listChannelViewModel, CreateChannelViewModel createChannelViewModel,
                           ListChannelDataAccessInterface listChannelDataAccessInterface, ViewProfileViewModel viewProfileViewModel) {

        ListChannelController listChannelController =
                createListChannelController(viewManagerModel, listChannelViewModel, listChannelDataAccessInterface);
        MenuView menuView = new MenuView(viewManagerModel, menuViewModel, listChannelViewModel, createChannelViewModel, listChannelController, viewProfileViewModel);
        return menuView;

    }


    public static ListChannelController
    createListChannelController(ViewManagerModel viewManagerModel, ListChannelViewModel listChannelViewModel,
                                ListChannelDataAccessInterface listChannelDataAccessObject) {


        ListChannelOutputBoundary listChannelOutputBoundary = new ListChannelPresenter(listChannelViewModel,
                viewManagerModel);
        ListChannelInputBoundary listChannelInputBoundary = new ListChannelInteractor(listChannelDataAccessObject,
                listChannelOutputBoundary);


        return new ListChannelController(listChannelInputBoundary);
    }

}
