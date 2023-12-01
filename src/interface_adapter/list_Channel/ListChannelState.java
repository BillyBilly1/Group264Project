package interface_adapter.list_Channel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListChannelState {

    public ArrayList<ChannelInfo> getChannels(String json) {
        ArrayList<ChannelInfo> channels = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject channelObj = jsonArray.getJSONObject(i);
            String channelUrl = channelObj.getString("channel_url");
            String name = channelObj.getString("name");

            ChannelInfo channel = new ChannelInfo(channelUrl, name);
            channels.add(channel);
        }

        return channels;
    }
}
