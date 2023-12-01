package interface_adapter.list_Channel;

import use_case.ListChannel.ListChannelInputBoundary;
import use_case.ListChannel.ListChannelInputData;

public class ListChannelController {

    private final ListChannelInputBoundary listChannelInterator;

    public ListChannelController(ListChannelInputBoundary listChannelInterator) {
        this.listChannelInterator = listChannelInterator;
    }

    public void execute(String userID) {
        ListChannelInputData listChannelInputData = new ListChannelInputData(userID);
        listChannelInterator.execute(listChannelInputData);
    }
}
