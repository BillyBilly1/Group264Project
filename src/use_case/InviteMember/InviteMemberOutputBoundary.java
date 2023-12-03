package use_case.InviteMember;

public interface InviteMemberOutputBoundary {
    void prepareSuccessView(String success, String userId);

    void prepareFailView(String error);
}
