package interface_adapter.Menu;

import entity.Channel.Channel;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel {

    private ArrayList<Channel> channelList;

    private String userID;


    public String getUserID() {
        return this.userID;
    }


    public List<String> getChannelNameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Channel channel : channelList) {
            nameList.add(channel.getChannelName());}
        return nameList;


        }
    }
