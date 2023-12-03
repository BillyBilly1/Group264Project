package use_case.ViewChannel;

public class ViewChannelInputData {

    private final String userID;
    private final String channel_url;


    public ViewChannelInputData(String userID, String channelUrl) {
        this.userID = userID;
        this.channel_url = channelUrl;
    }

    public String getChannel_url(){ return channel_url; }

    public String getUserID() {return this.userID;}
}
