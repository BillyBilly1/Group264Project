package entity.User;

import entity.User.CommonUser;
import entity.User.User;
import entity.User.UserFactory;

public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String user_id, String nickname, String profileUrl) {
        return new CommonUser(user_id, nickname, profileUrl);
    }
}
