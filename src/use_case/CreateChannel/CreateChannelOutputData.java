package use_case.CreateChannel;

import entity.Channel.Channel;

public class CreateChannelOutputData {
    private final boolean success;
    private final String message;

    private final Channel channel;

    public CreateChannelOutputData(Channel channel, String message, boolean success) {
        this.channel = channel;
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

    public Channel getChannel() {return channel;}

}
