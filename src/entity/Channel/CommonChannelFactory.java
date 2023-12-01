package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChannelFactory implements ChannelFactory {

    public Channel create(String channelName, String channelUrl,
                          List<String> operator, String isEphemeral){
        return new CommonChannel(channelName, channelUrl, operator, isEphemeral);}
}

