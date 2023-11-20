package use_case.clear_users;


import java.util.ArrayList;

public class ClearOutputData {
    private final boolean success;

    private final ArrayList<String> deadList;

    public ClearOutputData(boolean success, ArrayList<String> deadList) {
        this.success = success;
        this.deadList = deadList;
    }

    public boolean get_success() {
        return success;
    }

    public ArrayList<String> get_deadList() {return deadList;}
}
