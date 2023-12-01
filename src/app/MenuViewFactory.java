package app;

import entity.Channel.Channel;
import entity.Channel.ChannelFactory;
import entity.Channel.CommonChannelFactory;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelPresenter;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelController;
import interface_adapter.list_Channel.ListChannelPresenter;
import interface_adapter.list_Channel.ListChannelViewModel;
import use_case.CreateChannel.CreateChannelDataAccessInterface;
import use_case.CreateChannel.CreateChannelInputBoundary;
import use_case.CreateChannel.CreateChannelInteractor;
import use_case.CreateChannel.CreateChannelOutputBoundary;
import use_case.ListChannel.*;
import view.MenuView;

import java.util.ArrayList;

public class MenuViewFactory {

    public static MenuView create(ViewManagerModel viewManagerModel, MenuViewModel menuViewModel,
                           ListChannelViewModel listChannelViewModel, CreateChannelViewModel createChannelViewModel,
                           ListChannelDataAccessInterface listChannelDataAccessInterface) {

        ListChannelController listChannelController =
                createListChannelController(viewManagerModel, listChannelViewModel, listChannelDataAccessInterface);
        MenuView menuView = new MenuView(menuViewModel, listChannelViewModel, createChannelViewModel, listChannelController);
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
