package entity.User;

import entity.User.User;

public class CommonUser implements User {
    private final String user_id;
    private final String nickname;
    private String profileUrl;

    public CommonUser(String user_id, String nickname, String profileUrl) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }


    public String getUser_Id() {return user_id;}

    public String getNickName() {return nickname;}

    public String getProfileUrl() { return nickname;}

}
