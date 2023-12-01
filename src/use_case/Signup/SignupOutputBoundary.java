package use_case.Signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputdata user);

    void prepareFailView(String error);

    void skip();
}
