package use_case.ViewProfile;

import entity.User.UserFactory;
import use_case.Signup.SignupDataAccessInterface;
import use_case.Signup.SignupOutputBoundary;

public class ViewProfileInteractor implements ViewProfileInputBoundary {

    static ViewProfileDataAccessInterface viewProfileDataAccessObject;
    static ViewProfileOutputBoundary userPresenter;

    public ViewProfileInteractor(ViewProfileDataAccessInterface viewProfileDataAccessInterface,
                                 ViewProfileOutputBoundary viewProfileOutputBoundary) {
        viewProfileDataAccessObject = viewProfileDataAccessInterface;
        userPresenter = viewProfileOutputBoundary;
    }

    @Override
    public void execute(ViewProfileInputData viewProfileInputData) {
        if (viewProfileDataAccessObject.existsByName(ViewProfileInputData.getNickname())) {
            ViewProfileOutputData viewProfileOutputData = new ViewProfileOutputData(ViewProfileInputData.getUser_id(),
                    ViewProfileInputData.getNickname());
            userPresenter.prepareSuccessView(viewProfileOutputData);
        }else{
            userPresenter.prepareFailView("User do not exists.");
        }


    }
}
