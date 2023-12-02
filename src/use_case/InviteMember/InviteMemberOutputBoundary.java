package use_case.InviteMember;

public interface InviteMemberOutputBoundary {
    void prepareSuccessView(String error);

    void prepareFailView(String error);
}
