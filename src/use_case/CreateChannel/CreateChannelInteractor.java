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
        if (inputData.isFilled()) {
            Channel channel = channelFactory.create(
                    inputData.getChannelName(),
                    inputData.getChannelUrl(),
                    inputData.getOperator(),
                    inputData.getIsEphemeral()
            );
        boolean success = channeldataAccessObject.createChannel(channel);

        if (success) {
            CreateChannelOutputData createChannelOutputData = new CreateChannelOutputData(channel.getChannelName(), "channel created",
                    true);
            channelPresenter.prepareSuccessView(createChannelOutputData);
        }
        else {
            channelPresenter.prepareFailView("Error");
        }
    }      
}
