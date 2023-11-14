package use_case.Login;

public class LoginInputData {

    final private String user_id;

    final private String nickname;


    public LoginInputData(String user_id, String nickname) {
        this.user_id = user_id;
        this.nickname = nickname;
    }

    String getUser_id(){ return user_id; }
    String getNickname() {
        return nickname;
    }
}
