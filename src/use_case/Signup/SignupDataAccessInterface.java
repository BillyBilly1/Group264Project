package use_case.Signup;

import entity.User.User;

public interface SignupDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}