package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChannelFactory implements ChannelFactory {

    public Channel create(String channelName, String channelUrl,
                          String is_distinct, String isEphemeral){
        return new CommonChannel(channelName, channelUrl, is_distinct,isEphemeral);}
}

