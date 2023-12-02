package entity.Channel;
import java.time.LocalDateTime;
import java.util.List;

public interface ChannelFactory {

    Channel create(List<String> user_ids,String channelName, String channelUrl, List<String> operator,
                   String is_distinct,
                   String isEphemeral);
}
