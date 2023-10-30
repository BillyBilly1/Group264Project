package entity.User;

import entity.User.User;

public interface UserFactory {
    User create(String user_id, String nickname, String profileUrl);
}
