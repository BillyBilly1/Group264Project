package interface_adapter.Login;

public class LoginState {
    private String user_id = "";
    private String user_idError = null;
    private String nickname = "";
    private String nicknameError = null;

    public LoginState(LoginState copy) {
        user_id = copy.user_id;
        user_idError = copy.user_idError;
        nickname = copy.nickname;
        nicknameError = copy.nicknameError;
    }

    public LoginState() {}

    public String getUser_id() {
        return user_id;
    }

    public String getUser_idError() {
        return user_idError;
    }

    public String getNickname() {
        return nickname;
    }
    public String getNicknameError() { return nicknameError; }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_idError(String user_idError) {
        this.user_idError = user_idError;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setNicknameError(String nicknameError) { this.nicknameError = nicknameError;}
}
