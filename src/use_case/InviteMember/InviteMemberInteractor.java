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
        if(!inviteMemberDataAccessInterface.is_member(inviteMemberInputdata)){
            if(inviteMemberDataAccessInterface.invite(inviteMemberInputdata)) {
                inviteMemberOutputBoundary.prepareSuccessView(
                        inviteMemberInputdata.getUser_id() + "is now " +
                                "in your channel!", inviteMemberInputdata.getUser_id());
            }else {
                inviteMemberOutputBoundary.prepareFailView("User does not exist.");
            }
        }else{
            inviteMemberOutputBoundary.prepareFailView(inviteMemberInputdata.getUser_id() + " " +
                    "is already in the channel.");
        }
    }


}
