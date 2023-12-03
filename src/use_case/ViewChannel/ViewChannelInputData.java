package use_case.ViewChannel;

public class ViewChannelInputData {
    private final String channel_url;


    public ViewChannelInputData(String channelUrl) {
        this.channel_url = channelUrl;
    }

    public String getChannel_url(){ return channel_url; }
}
