package interface_adapter.Invite_Member;

import use_case.InviteMember.InviteMemberInputBoundary;
import use_case.InviteMember.InviteMemberInputdata;

public class InviteMemberController {

    private InviteMemberInputBoundary inviteMemberInputBoundary;

    public InviteMemberController(InviteMemberInputBoundary inviteMemberInputBoundary){
        this.inviteMemberInputBoundary = inviteMemberInputBoundary;
    }

    public void execute(String user_id, String channel_url){
        InviteMemberInputdata inviteMemberInputdata = new InviteMemberInputdata(user_id, channel_url);
        inviteMemberInputBoundary.execute(inviteMemberInputdata);
    }
}
