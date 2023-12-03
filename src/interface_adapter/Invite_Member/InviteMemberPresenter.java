package interface_adapter.Invite_Member;

import interface_adapter.ViewManagerModel;
import use_case.InviteMember.InviteMemberOutputBoundary;
import use_case.InviteMember.InviteMemberOutputdata;
import view.ViewManager;

import javax.swing.*;

public class InviteMemberPresenter implements InviteMemberOutputBoundary {
    private ViewManagerModel viewManagerModel;

    public InviteMemberPresenter(ViewManagerModel viewManagerModel){
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(String success){
        JOptionPane.showMessageDialog(null, success,
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void prepareFailView(String error){
        JOptionPane.showMessageDialog(null, error, "Error",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
