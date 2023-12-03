package use_case.RemoveMember;

public interface RemoveMemberOutputBoundary {
    void prepareSuccessView(String success, String userID);
    void prepareFailView(String error);
}
