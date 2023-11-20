package use_case.Login;

import entity.User.User;

public interface LoginDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
