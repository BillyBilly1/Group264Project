package interface_adapter.list_Channel;

import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import use_case.ListChannel.ListChannelOutputBoundary;
import use_case.ListChannel.ListChannelOutputData;

public class ListChannelPresenter implements ListChannelOutputBoundary {

    private final ListChannelViewModel listChannelViewModel;

    private final ViewManagerModel viewManagerModel;


    public ListChannelPresenter(ListChannelViewModel listChannelViewModel, ViewManagerModel viewManagerModel) {
        this.listChannelViewModel = listChannelViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(ListChannelOutputData channel_list) {

        ListChannelState currentState = listChannelViewModel.getListChannelState();
        JSONArray jsonArray = channel_list.get_channel_list();
        String jsonString = jsonArray.toString();
        currentState.setChannels(jsonString);
        listChannelViewModel.setListChannelState(currentState);

    }

    @Override
    public void prepareFailView(String error) {

    }
}
