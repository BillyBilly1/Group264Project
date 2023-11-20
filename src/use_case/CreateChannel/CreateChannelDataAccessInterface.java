package use_case.CreateChannel;

import entity.Channel.Channel;

public interface CreateChannelDataAccessInterface {
    /**
     * Creates a new channel in the data store.
     * @param channel The channel entity to be created.
     * @return A boolean indicating whether the channel was successfully created.
     */
    boolean createChannel(Channel channel);

}

