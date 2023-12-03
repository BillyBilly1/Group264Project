package interface_adapter.Remove_Member;

import interface_adapter.ViewManagerModel;
import use_case.RemoveMember.RemoveMemberOutputBoundary;

import javax.swing.*;

public class RemoveMemberPresenter implements RemoveMemberOutputBoundary {
    private ViewManagerModel viewManagerModel;

    public RemoveMemberPresenter() {
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

