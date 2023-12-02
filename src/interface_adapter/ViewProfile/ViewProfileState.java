package interface_adapter.ViewProfile;

import entity.User.CommonUser;

public class ViewProfileState {
    private String user_id;
    private String nickname;

    public ViewProfileState() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
