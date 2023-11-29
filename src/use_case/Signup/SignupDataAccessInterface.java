package use_case.Signup;

import entity.User.User;

public interface SignupDataAccessInterface {

    String get_username(String user_id);

    boolean save(User user);
}