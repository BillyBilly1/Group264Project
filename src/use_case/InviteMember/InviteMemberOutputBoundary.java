package use_case.InviteMember;

public interface InviteMemberOutputBoundary {
    void prepareSuccessView(String success);

    void prepareFailView(String error);
}
