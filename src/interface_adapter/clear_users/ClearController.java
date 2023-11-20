package interface_adapter.clear_users;

import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInteractor;

public class ClearController {

    final ClearInputBoundary ClearUseCaseInteractor;

    public ClearController(ClearInputBoundary ClearUseCaseInteractor) {
        this.ClearUseCaseInteractor = ClearUseCaseInteractor;
    }

    public void execute(){  // No Input Data used
        ClearUseCaseInteractor.execute();
    }
}
