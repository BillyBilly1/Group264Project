package interface_adapter.logged_in;

public class LoggedInState {
    private String user_id = "";

    public LoggedInState(LoggedInState copy) {
        user_id = copy.user_id;
    }

    public LoggedInState() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
