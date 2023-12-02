package use_case.CreateChannel;

import entity.Channel.Channel;
import interface_adapter.list_Channel.ChannelInfo;

public class CreateChannelOutputData {
    private final boolean success;
    private final String message;

    private final ChannelInfo channelInfo;

    public CreateChannelOutputData(ChannelInfo channelInfo, String message, boolean success) {
        this.channelInfo = channelInfo;
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ChannelInfo getChannel() {return channelInfo;}

}
