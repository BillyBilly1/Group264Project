package use_case.ViewProfile;

import use_case.Signup.SignupOutputdata;

public interface ViewProfileOutputBoundary {
    void prepareSuccessView(ViewProfileOutputData String);

    void prepareFailView(String error);
}
