package interface_adapter.Remove_Member;

import use_case.RemoveMember.RemoveMemberInputBoundary;
import use_case.RemoveMember.RemoveMemberInputdata;

public class RemoveMemberController {
    private RemoveMemberInputBoundary removeMemberInputBoundary;

    public RemoveMemberController(RemoveMemberInputBoundary removeMemberInputBoundary){
        this.removeMemberInputBoundary = removeMemberInputBoundary;

    }

    public void execute(String user_id, String channel_url){
        RemoveMemberInputdata removeMemberInputdata = new RemoveMemberInputdata(user_id, channel_url);
        removeMemberInputBoundary.execute(removeMemberInputdata);
    }
}
