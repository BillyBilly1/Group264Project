package use_case.RemoveMember;

public interface RemoveMemberOutputBoundary {
    void prepareSuccessView(String success);
    void prepareFailView(String error);
}
