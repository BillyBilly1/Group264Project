package use_case.CreateChannel;

import entity.Channel.*;

public class CreateChannelOutputData {
    private final boolean success;
    private final String message;

    private Channel channel;

    public CreateChannelOutputData(Channel channel, String message, boolean success) {
        this.success = success;
        this.message = message;
        this.channel = channel;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Channel getChannel() {
        return channel;
    }
}
