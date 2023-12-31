package use_case;

public class SendMessageInputdata {

    private final String userID;

    private final String message;

    private final String channelType = "group_channels";

    private final String channelUrl;

    private final long messageTs;


    public SendMessageInputdata(String userID, String message, String channelUrl, long messageTs) {
        this.userID = userID;
        this.message = message;
        this.channelUrl = channelUrl;
        this.messageTs = messageTs;
    }

    public String getUserID() {
        return this.userID;}

    public String getMessage() {
        return this.message;}

    public String getChannelType() {
        return this.channelType;
    }

    public String getChannelUrl() {
        return this.channelUrl;
    }

    public long getMessageTs() {return this.messageTs;}
}
