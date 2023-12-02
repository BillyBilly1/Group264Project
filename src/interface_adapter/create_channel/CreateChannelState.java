package interface_adapter.create_channel;

import java.util.ArrayList;
import java.util.List;

public class CreateChannelState {
    private String channel_url = "";
    private String channelName = "";

    private List<String> user_ids = new ArrayList<>();

    private List<String> operator = new ArrayList<>();

    private String is_distinct = "false";

    private String isEphemeral = "false";



    public CreateChannelState(CreateChannelState copy) {
        user_ids = copy.user_ids;
        channelName = copy.channelName;
        channel_url = copy.channel_url;
        operator = copy.operator;
        is_distinct = copy.is_distinct;
        isEphemeral = copy.isEphemeral;
    }

    public CreateChannelState() {
    }

    // Getters
    public String getChannel_url() {
        return channel_url;
    }

    public List<String> getOperator(){
        return operator;
    }

    public List<String> getUser_ids() { return user_ids; }

    public String getChannelName() {
        return channelName;
    }

    public String getIs_distinct() { return is_distinct; }

    public String getIsEphemeral() { return isEphemeral; }


    // Setters
    public void setChannel_url(String channel_url) {
        this.channel_url = channel_url;
    }

    public void setOperator(List<String> operators){
        this.operator = operators;
    }

    public void setUser_ids(List<String> members){ this.user_ids = user_ids; }



    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    @Override
    public String toString() {
        return "CreateChannelState{" +
                "channelId='" + channel_url + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
