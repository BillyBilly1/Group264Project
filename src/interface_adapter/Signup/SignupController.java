package interface_adapter.Signup;

import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary SignupUseCaseInteractor;
    public SignupController(SignupInputBoundary SignupUseCaseInteractor) {
        this.SignupUseCaseInteractor = SignupUseCaseInteractor;
    }

    public void execute(String user_id, String nickname) {
        SignupInputData signupInputData = new SignupInputData(user_id, nickname);

        SignupUseCaseInteractor.execute(signupInputData);
    }
}
