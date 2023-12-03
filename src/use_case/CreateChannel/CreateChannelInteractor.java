package use_case.CreateChannel;

import entity.Channel.*;
import interface_adapter.list_Channel.ChannelInfo;


public class CreateChannelInteractor implements CreateChannelInputBoundary {
    final CreateChannelDataAccessInterface channeldataAccessObject;
    final CreateChannelOutputBoundary channelPresenter;
    final ChannelFactory channelFactory;

    public CreateChannelInteractor(CreateChannelDataAccessInterface channeldataAccessObject,
                                   CreateChannelOutputBoundary outputBoundary,
                                   ChannelFactory channelFactory) {
        this.channeldataAccessObject = channeldataAccessObject;
        this.channelPresenter = outputBoundary;
        this.channelFactory = channelFactory;
    }

    @Override
    public void execute(CreateChannelInputData inputData) {
            Channel channel = channelFactory.create(
                    inputData.getChannelName(),
                    inputData.getChannelUrl(),
                    inputData.getIs_distinct(),
                    inputData.getIsEphemeral());
            channel.setOperator(inputData.getOperator());
            channel.setUser_id(inputData.getUser_ids());

        ChannelInfo channelInfo = new ChannelInfo(inputData.getChannelUrl(), inputData.getChannelName());

            boolean success = channeldataAccessObject.createChannel(channel);

            if (success) {
                CreateChannelOutputData createChannelOutputData = new CreateChannelOutputData(channelInfo, "channel created",
                        true);
                channelPresenter.prepareSuccessView(createChannelOutputData);
            } else {
                channelPresenter.prepareFailView("Error");
            }
        }

    @Override
    public void back() {
        channelPresenter.back();

    }
}
