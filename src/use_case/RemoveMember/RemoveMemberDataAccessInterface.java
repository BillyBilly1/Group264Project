package use_case.RemoveMember;

public interface RemoveMemberDataAccessInterface {

    boolean is_member(RemoveMemberInputdata removeMemberInputdata);
    boolean remove(RemoveMemberInputdata removeMemberInputdata);
}
