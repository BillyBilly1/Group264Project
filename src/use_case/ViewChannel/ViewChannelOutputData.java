package use_case.ViewChannel;

import entity.Channel.*;

public class ViewChannelOutputData {

    private final boolean success;

    private final String message;
    private final Channel channel;

    private final String userID;



    public ViewChannelOutputData(String userID, Channel channel, String message, boolean success) {
        this.userID = userID;
        this.channel = channel;
        this.success = success;
        this.message = message;
    }

    // Getters

    public String getUserID() {return userID;}
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Channel getChannel() {return channel;}
}
