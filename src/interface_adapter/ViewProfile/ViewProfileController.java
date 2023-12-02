package interface_adapter.ViewProfile;
import use_case.ViewProfile.ViewProfileInputBoundary;
import use_case.ViewProfile.ViewProfileInputData;
import entity.User.User;

public class ViewProfileController {

    final ViewProfileInputBoundary viewProfileUseCaseInteraactor;
    static User user;

    public ViewProfileController(ViewProfileInputBoundary viewProfileUseCaseInteraactor) {
        this.viewProfileUseCaseInteraactor = viewProfileUseCaseInteraactor;
    }

    public void execute() {
        ViewProfileInputData viewProfileInputData = new ViewProfileInputData(user);
        viewProfileUseCaseInteraactor.execute(viewProfileInputData);
    }
}
