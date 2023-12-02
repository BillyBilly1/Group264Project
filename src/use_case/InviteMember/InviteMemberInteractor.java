package use_case.InviteMember;

public class InviteMemberInteractor implements InviteMemberInputBoundary {
    final InviteMemberDataAccessInterface inviteMemberDataAccessInterface;
    final InviteMemberOutputBoundary inviteMemberOutputBoundary;

    public InviteMemberInteractor(InviteMemberDataAccessInterface inviteMemberDataAccessInterface,
                                  InviteMemberOutputBoundary inviteMemberOutputBoundary){
        this.inviteMemberOutputBoundary = inviteMemberOutputBoundary;
        this.inviteMemberDataAccessInterface = inviteMemberDataAccessInterface;
    }

    @Override
    public void execute(InviteMemberInputdata inviteMemberInputdata){
        if(inviteMemberDataAccessInterface.invite(inviteMemberInputdata)){
            inviteMemberOutputBoundary.prepareSuccessView("Invitation success");
        }else{
            inviteMemberOutputBoundary.prepareFailView("Invitation error.");
        }
    }


}
