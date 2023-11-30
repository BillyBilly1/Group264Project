package entity.Channel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonChannelFactory implements ChannelFactory {

    public Channel create(String channelName, String channelUrl,
                          ArrayList<String> operator, String isEphemeral){
        return new CommonChannel(channelName, channelUrl, operator, isEphemeral);}
}

