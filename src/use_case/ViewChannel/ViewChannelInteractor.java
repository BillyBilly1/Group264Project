package use_case.ViewChannel;


import entity.Channel.Channel;
import use_case.GetOperator.GetOperatorDataAccessInterface;
import use_case.ViewMembers.ViewMembersDataAccessInterface;

import java.util.List;

public class ViewChannelInteractor {
    final ViewChannelDataAccessInterface channelDataAccessObject;
    final ViewChannelOutputBoundary viewChannelPresenter;
    final ViewMembersDataAccessInterface membersDataAccessObject;
    final GetOperatorDataAccessInterface operatorDataAccessObject;

    public ViewChannelInteractor(ViewChannelDataAccessInterface channelDataAccessObject,
                                 ViewChannelOutputBoundary outputBoundary,
                                 ViewMembersDataAccessInterface membersDataAccessInterface,
                                 GetOperatorDataAccessInterface operatorDataAccessInterface) {
        this.channelDataAccessObject = channelDataAccessObject;
        this.viewChannelPresenter = outputBoundary;
        this.membersDataAccessObject = membersDataAccessInterface;
        this.operatorDataAccessObject = operatorDataAccessInterface;
    }

    public void execute(ViewChannelInputData inputData) {
        String channel_url = inputData.getChannel_url();

        Channel channel = channelDataAccessObject.viewChannel(channel_url);

        if (channel != null) {
            ViewChannelOutputData viewChannelOutputData = new ViewChannelOutputData(channel, "channel find",
                    true);

            List<String> members = membersDataAccessObject.viewMembers(channel_url);
            channel.setUser_id(members);
            List<String> operator = operatorDataAccessObject.getOperator(channel_url);
            channel.setOperator(operator);
            viewChannelPresenter.prepareSuccessView(viewChannelOutputData);
        } else {
            viewChannelPresenter.prepareFailView("Error");
        }
    }
}