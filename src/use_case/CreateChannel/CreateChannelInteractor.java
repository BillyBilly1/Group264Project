package use_case.CreateChannel;

import entity.Channel.*;



public class CreateChannelInteractor implements CreateChannelInputBoundary {
    final CreateChannelDataAccessInterface channeldataAccessObject;
    final CreateChannelOutputBoundary outputBoundary;
    final ChannelFactory channelFactory;

    public CreateChannelInteractor(CreateChannelDataAccessInterface channeldataAccessObject,
                                   CreateChannelOutputBoundary outputBoundary,
                                   ChannelFactory channelFactory) {
        this.channeldataAccessObject = channeldataAccessObject;
        this.outputBoundary = outputBoundary;
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

            try {

                // After saving the channel, we check if it was successful.
                boolean isChannelCreated = channeldataAccessObject.createChannel(channel);
                CreateChannelOutputData outputData;

                if (isChannelCreated) {
                    // If the channel was successfully created, we assume we have a channel ID.
                    // You should adjust the method to get the actual channel ID.
                    outputData = new CreateChannelOutputData(true, "Channel created successfully.", channel.getChannelName());
                } else {
                    // If the creation was not successful, we do not have a channel ID.
                    outputData = new CreateChannelOutputData(false, "Channel creation failed.", null);
                }
                outputBoundary.present(outputData);

            } catch (Exception e) {
                // Handle any exceptions thrown during channel saving/creation.
                CreateChannelOutputData outputData = new CreateChannelOutputData(false, "Error: " + e.getMessage(), null);
                outputBoundary.present(outputData);
            }
        } else {
            // Handle the case where input data fields are not properly filled.
            CreateChannelOutputData outputData = new CreateChannelOutputData(false, "Invalid input data.", null);
            outputBoundary.present(outputData);
        }
    }
}


