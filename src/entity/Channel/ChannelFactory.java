package entity.Channel;
import java.time.LocalDateTime;
import java.util.List;

public interface ChannelFactory {

    Channel create(String channelName, String channelUrl, List<String> operator,
                   String isEphemeral);
}
