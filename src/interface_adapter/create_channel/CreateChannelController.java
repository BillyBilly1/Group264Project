package interface_adapter.create_channel;

import use_case.CreateChannel.CreateChannelInputBoundary;
import use_case.CreateChannel.CreateChannelInputData;

import java.util.ArrayList;

public class CreateChannelController {

    private CreateChannelInputBoundary interactor;


    public CreateChannelController(CreateChannelInputBoundary interactor) {
        this.interactor = interactor;
    }

    // This method is called when the user clicks the "Create" button in your view.
    public void  execute(String channel_id, String channelName, ArrayList<String> operator, String isEphemeral) {
        CreateChannelInputData createChannelInputData = new CreateChannelInputData(channelName, channel_id,
                operator, isEphemeral );
        interactor.execute(createChannelInputData);
    }

}

