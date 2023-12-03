package use_case.InviteMember;

public interface InviteMemberDataAccessInterface {

    boolean is_member(InviteMemberInputdata inviteMemberInputdata);
    boolean invite(InviteMemberInputdata inviteMemberInputdata);
}
