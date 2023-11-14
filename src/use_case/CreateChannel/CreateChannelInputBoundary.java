package use_case.CreateChannel;

import entity.Channel.CommonChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface CreateChannelInputBoundary {
    CommonChannel createChannel(String channelName, String channelUrl,
                                ArrayList<String> admin, boolean isEphemeral,
                                LocalDateTime creationTime);
}
