package use_case.ViewChannel;

import entity.Channel.Channel;

public interface ViewChannelDataAccessInterface {

    Channel viewChannel(String channel_url);
}
