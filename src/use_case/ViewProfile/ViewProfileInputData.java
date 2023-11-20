package use_case.ViewProfile;

import entity.User.User;

public class ViewProfileInputData {
    static User user;

    public ViewProfileInputData(User user) {
        ViewProfileInputData.user = user;
    }

    static String getUser_id(){ return user.getUser_Id(); }
    static String getNickname() { return user.getNickName();
    }
}
