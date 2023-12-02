package interface_adapter.create_channel;

import use_case.CreateChannel.CreateChannelInputBoundary;
import use_case.CreateChannel.CreateChannelInputData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CreateChannelController {

    private CreateChannelInputBoundary interactor;


    public CreateChannelController(CreateChannelInputBoundary interactor) {
        this.interactor = interactor;
    }

    // This method is called when the user clicks the "Create" button in your view.
    public void  execute(List<String> user_ids, String channelName, String channel_url, List<String> operator,
                         String is_distinct,String isEphemeral) {
        CreateChannelInputData createChannelInputData = new CreateChannelInputData(user_ids, channelName, channel_url,
                operator, is_distinct, isEphemeral);
        interactor.execute(createChannelInputData);
    }
}

