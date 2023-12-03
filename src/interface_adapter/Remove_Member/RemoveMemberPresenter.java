package interface_adapter.Remove_Member;

import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.RemoveMember.RemoveMemberOutputBoundary;

import javax.swing.*;

public class RemoveMemberPresenter implements RemoveMemberOutputBoundary {

    private ChannelViewModel channelViewModel;
    public RemoveMemberPresenter(ChannelViewModel channelViewModel) {
        this.channelViewModel = channelViewModel;
    }

    @Override
    public void prepareSuccessView(String success, String userID) {
        JOptionPane.showMessageDialog(null, success,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        channelViewModel.getChannel().getUser_ids().remove(userID);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "Error",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

