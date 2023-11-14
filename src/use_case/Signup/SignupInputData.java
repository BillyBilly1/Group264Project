package use_case.Signup;

public class SignupInputData {
    final private String user_id;
    final private String nickname;

    public SignupInputData(String user_id, String nickname) {
        this.user_id = user_id;
        this.nickname = nickname;
    }

    String getUser_id(){ return user_id; }
    String getNickname() {
        return nickname;
    }

}
