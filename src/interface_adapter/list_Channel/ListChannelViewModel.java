package interface_adapter.list_Channel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListChannelViewModel {

    private ListChannelState listChannelState = new ListChannelState();



    public ListChannelViewModel() {}

    public void setListChannelState(ListChannelState listChannelState) {
        this.listChannelState = listChannelState;
    }

    public ListChannelState getListChannelState() {
        return this.listChannelState;
    }



}
