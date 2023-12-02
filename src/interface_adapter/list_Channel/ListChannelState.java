package interface_adapter.list_Channel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListChannelState {

    private ArrayList<ChannelInfo> channelList = new ArrayList<ChannelInfo>();

    private String userID;

    public ListChannelState() {
    }



    public void setChannels(String json) {
        ArrayList<ChannelInfo> channels = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject channelObj = jsonArray.getJSONObject(i);
            String channelUrl = channelObj.getString("channel_url");
            String name = channelObj.getString("name");
            ChannelInfo channel = new ChannelInfo(channelUrl, name);
            channels.add(channel);
        }

        this.channelList = channels;
    }

    public ArrayList<ChannelInfo> getChannels() {return this.channelList;}

    public void setUserID(String userID) {this.userID = userID;}


    public String getUserID() {return this.userID;}
}
