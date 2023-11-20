package use_case.ViewProfile;

public class ViewProfileOutputData {

    private final String user_id;
    private final String nickname;

    public ViewProfileOutputData (String user_id, String nickname) {
        this.user_id = user_id;
        this.nickname = nickname;
    }

    public String Nickname() {
        return nickname;
    }

    public String User_id() {return user_id;}
}
