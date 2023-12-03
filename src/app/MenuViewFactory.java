package app;

import data_access.FileChannelDataAccessObject;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewChannel.ViewChannelPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelController;
import interface_adapter.list_Channel.ListChannelPresenter;
import interface_adapter.list_Channel.ListChannelViewModel;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import interface_adapter.viewChannel.ViewChannelController;
import use_case.GetOperator.GetOperatorDataAccessInterface;
import use_case.ListChannel.*;
import use_case.ViewChannel.ViewChannelDataAccessInterface;
import use_case.ViewChannel.ViewChannelInputBoundary;
import use_case.ViewChannel.ViewChannelInteractor;
import use_case.ViewChannel.ViewChannelOutputBoundary;
import use_case.ViewMembers.ViewMembersDataAccessInterface;
import view.MenuView;

import java.io.IOException;

public class MenuViewFactory {

    public static MenuView create(ViewManagerModel viewManagerModel, MenuViewModel menuViewModel,
                           ListChannelViewModel listChannelViewModel, CreateChannelViewModel createChannelViewModel,
                           ListChannelDataAccessInterface listChannelDataAccessInterface, ViewProfileViewModel viewProfileViewModel) throws IOException {

        ListChannelController listChannelController =
                createListChannelController(viewManagerModel, listChannelViewModel, listChannelDataAccessInterface);
        ViewChannelController viewChannelController = createViewChannelController();
        MenuView menuView = new MenuView(viewManagerModel, menuViewModel, listChannelViewModel,
                createChannelViewModel, listChannelController, viewProfileViewModel, viewChannelController);
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

    public static ViewChannelController createViewChannelController() throws IOException {
        ViewChannelDataAccessInterface viewChannelDataAccessInterface = new FileChannelDataAccessObject();
        ViewMembersDataAccessInterface membersDataAccessInterface = new FileChannelDataAccessObject();
        GetOperatorDataAccessInterface getOperatorDataAccessInterface = new FileChannelDataAccessObject();

        ViewChannelOutputBoundary viewChannelOutputBoundary = new ViewChannelPresenter();
        ViewChannelInputBoundary viewChannelInteractor = new ViewChannelInteractor(viewChannelDataAccessInterface,
                viewChannelOutputBoundary, membersDataAccessInterface, getOperatorDataAccessInterface);
        ViewChannelController viewChannelController = new ViewChannelController(viewChannelInteractor);
        return viewChannelController;

    }

}
