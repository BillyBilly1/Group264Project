package use_case.ListChannel;

import java.util.ArrayList;
import entity.Channel.Channel;
import org.json.JSONArray;

public class ListChannelOutputData {

    private final JSONArray channel_list;

    public ListChannelOutputData(JSONArray channel_list){
        this.channel_list = channel_list;
    }

    public JSONArray get_channel_list(){ return channel_list;}
}
