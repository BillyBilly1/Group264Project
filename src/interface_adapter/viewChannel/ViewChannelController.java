package interface_adapter.viewChannel;

import use_case.ViewChannel.ViewChannelInputBoundary;
import use_case.ViewChannel.ViewChannelInputData;
import use_case.ViewChannel.ViewChannelInteractor;
import use_case.ViewProfile.ViewProfileInputBoundary;

import java.io.IOException;

public class ViewChannelController {

    private final ViewChannelInputBoundary viewChannelInputBoundary;


    public ViewChannelController(ViewChannelInputBoundary viewChannelInputBoundary) {
        this.viewChannelInputBoundary = viewChannelInputBoundary;
    }

    public void execute(String channelUrl) throws IOException {
        ViewChannelInputData viewChannelInputData = new ViewChannelInputData(channelUrl);
        viewChannelInputBoundary.execute(viewChannelInputData);
    }
}
