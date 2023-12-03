package use_case.RemoveMember;

import use_case.InviteMember.InviteMemberInputdata;

public class RemoveMemberInteractor implements RemoveMemberInputBoundary{
    final RemoveMemberDataAccessInterface removeMemberDataAccessInterface;
    final RemoveMemberOutputBoundary removeMemberOutputBoundary;

    public RemoveMemberInteractor(RemoveMemberDataAccessInterface removeMemberDataAccessInterface,
                                  RemoveMemberOutputBoundary removeMemberOutputBoundary){
        this.removeMemberDataAccessInterface = removeMemberDataAccessInterface;
        this.removeMemberOutputBoundary = removeMemberOutputBoundary;
    }

    @Override
    public void execute(RemoveMemberInputdata removeMemberInputdata){
        if(removeMemberDataAccessInterface.is_member(removeMemberInputdata)){
            removeMemberDataAccessInterface.remove(removeMemberInputdata);
            removeMemberOutputBoundary.prepareSuccessView("Remove success.", removeMemberInputdata.getUser_id());
        }else{
            removeMemberOutputBoundary.prepareFailView("User not in the channel.");
            }

    }
}
