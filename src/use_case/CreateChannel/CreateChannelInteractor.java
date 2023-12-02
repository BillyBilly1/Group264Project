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
        System.out.println("到理塘了");


            Channel channel = channelFactory.create(
                    inputData.getUser_ids(),
                    inputData.getChannelName(),
                    inputData.getChannelUrl(),
                    inputData.getOperator(),
                    inputData.getIs_distinct(),
                    inputData.getIsEphemeral());

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
