package entity.Channel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ChannelFactory {

    Channel create(String channelName, String channelUrl, ArrayList<String> operator,
                   String isEphemeral, LocalDateTime creationTime);
}
