package use_case.CreateChannel;

import entity.Channel.*;



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

            boolean success = channeldataAccessObject.createChannel(channel);

            if (success) {
                CreateChannelOutputData createChannelOutputData = new CreateChannelOutputData(channel, "channel created",
                        true);
                channelPresenter.prepareSuccessView(createChannelOutputData);
            } else {
                channelPresenter.prepareFailView("Error");
            }
        }
    }
