package use_case.Login;

public class LoginOutputData {

    private final String user_id;

    private final String nickname;

    private boolean useCaseFailed;


    public LoginOutputData(String userId, String nickname, boolean useCaseFailed) {
        this.user_id = userId;
        this.nickname = nickname;
        this.useCaseFailed = useCaseFailed;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUser_id() {return user_id;}
}
