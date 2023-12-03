package interface_adapter.Invite_Member;

import interface_adapter.Channel.ChannelViewModel;
import use_case.InviteMember.InviteMemberOutputBoundary;

import javax.swing.*;
import java.util.List;

public class InviteMemberPresenter implements InviteMemberOutputBoundary {


    private ChannelViewModel channelViewModel;

    public InviteMemberPresenter(ChannelViewModel channelViewModel){ this.channelViewModel = channelViewModel;
    }

    @Override
    public void prepareSuccessView(String success, String userId){
        JOptionPane.showMessageDialog(null, success,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        channelViewModel.getChannel().getUser_ids().add(userId);

    }
    @Override
    public void prepareFailView(String error){
        JOptionPane.showMessageDialog(null, error, "Error",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
