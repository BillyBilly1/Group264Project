package use_case.CreateChannel;

import entity.Channel.CommonChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateChannelInteractor implements CreateChannelInputBoundary {

    @Override
    public CommonChannel createChannel(String channelName, String channelUrl,
                                       ArrayList<String> admin, boolean isEphemeral,
                                       LocalDateTime creationTime) {
        // 这里可以加入业务逻辑，例如验证输入数据
        return new CommonChannel(channelName, channelUrl, admin, isEphemeral, creationTime);
    }
}
