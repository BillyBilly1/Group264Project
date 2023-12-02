package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChannelFactory implements ChannelFactory {

    public Channel create(List<String> user_ids, String channelName, String channelUrl,
                          List<String> operator, String is_distinct, String isEphemeral){
        return new CommonChannel(user_ids, channelName, channelUrl, operator, is_distinct,isEphemeral);}
}

