package use_case.CreateChannel;

import entity.Channel.*;

import java.time.LocalDateTime;


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
    public void execute(CreateChannelInputData createChannelInputData) {
        if (createChannelInputData == null ||
                createChannelInputData.getChannelName() == null ||
                createChannelInputData.getChannelName().isEmpty()) {
            throw new IllegalArgumentException("The data is invalid, please try again");
        }

        // Use the ChannelFactory to create a new Channel entity
        LocalDateTime currentTime = LocalDateTime.now();
        Channel newChannel = channelFactory.create(
                createChannelInputData.getChannelName(),
                createChannelInputData.getChannelUrl(),
                createChannelInputData.getOperator(),
                createChannelInputData.getIsEphemeral(),
                currentTime
        );

        // Interact with the data access object to persist the new channel
        try {
            // Assuming createChannel returns a boolean or some object indicating success
            boolean isChannelCreated = channeldataAccessObject.createChannel(newChannel);

            // Check if the operation was successful and handle accordingly
            if (isChannelCreated) {
                // Create and send output data to the output boundary
                CreateChannelOutputData outputData = new CreateChannelOutputData(
                        true,
                        "Channel created successfully",
                        newChannel.getChannelName()
                );
                outputBoundary.presentChannelCreationResult(outputData);
            } else {
                // Handle the case where channel creation failed
                CreateChannelOutputData outputData = new CreateChannelOutputData(
                        false,
                        "Channel creation failed",
                        null
                );
                outputBoundary.presentChannelCreationResult(outputData);
            }
        } catch (Exception e) {
            // Handle exceptions, such as network errors or data access issues
            // Could potentially create and send an error outputData here too
        }
    }
}


