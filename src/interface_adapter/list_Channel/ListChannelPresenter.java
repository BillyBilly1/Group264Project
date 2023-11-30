package interface_adapter.list_Channel;

import use_case.ListChannel.ListChannelOutputBoundary;
import use_case.ListChannel.ListChannelOutputData;

public class ListChannelPresenter implements ListChannelOutputBoundary {
    @Override
    public void prepareSuccessView(ListChannelOutputData channel_list) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
