package use_case.Signup;

public class SignupOutputdata {
    private final String user_id;
    private final String nickname;
    private String profileUrl;

    private boolean useCaseFailed;

    public SignupOutputdata(String user_id, String nickname,String profileUrl, boolean useCaseFailed) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.useCaseFailed = useCaseFailed;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUser_id() {return user_id;}

    public String getProfileUrl() {return profileUrl;}
}
