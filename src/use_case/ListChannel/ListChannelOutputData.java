package use_case.ListChannel;

import java.util.ArrayList;

public class ListChannelOutputData {

    private final ArrayList<String> channel_list;

    public ListChannelOutputData(ArrayList<String> channel_list){
        this.channel_list = channel_list;
    }

    public ArrayList<String> get_channel_list(){ return channel_list;}
}
