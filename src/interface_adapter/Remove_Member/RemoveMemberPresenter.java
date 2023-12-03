package interface_adapter.Remove_Member;

import interface_adapter.ViewManagerModel;
import use_case.RemoveMember.RemoveMemberInputBoundary;
import use_case.RemoveMember.RemoveMemberInputdata;
import use_case.RemoveMember.RemoveMemberOutputBoundary;

import javax.swing.*;

public class RemoveMemberPresenter implements RemoveMemberOutputBoundary {
    private ViewManagerModel viewManagerModel;

    public RemoveMemberPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(String success) {
        JOptionPane.showMessageDialog(null, success,
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "Error",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

