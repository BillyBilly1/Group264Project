package use_case;

public class SendMessageInputdata {

    private final String userID;

    private final String message;

    private final String channelType = "group_channels";

    private final String channelUrl;


    public SendMessageInputdata(String userID, String message, String channelUrl) {
        this.userID = userID;
        this.message = message;
        this.channelUrl = channelUrl;
    }
}
